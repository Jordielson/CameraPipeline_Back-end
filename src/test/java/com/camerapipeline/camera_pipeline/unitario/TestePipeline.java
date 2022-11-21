package com.camerapipeline.camera_pipeline.unitario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.camerapipeline.camera_pipeline.CameraPipelineApplication;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Coordinate;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;
import com.camerapipeline.camera_pipeline.provider.services.input.camera.CameraService;
import com.camerapipeline.camera_pipeline.provider.services.pdi.PDIService;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration
@SpringBootTest(classes = CameraPipelineApplication.class)
public class TestePipeline {

	@Autowired
    private AuthService authService;
	
	@Autowired
	private PipelineService pipelineService;
	
	@Autowired
	private PDIService pdiService;
	
	Pageable pageable = Pageable.unpaged();
	
	private Principal principal = null;
	
	private Coordinate coordinate = new Coordinate(50.00,50.00);
	
	@BeforeEach
	public void preConfig() {
		
		principal = authService.authenticateUser("admin@admin.com", "123456");
		apagarBanco();
	}
	
	/** sessão de sucesso **/
	
	@Test
	public void testeCriarPipelineComSucesso() {
		
		//Dado
		Pipeline pipeline = montarPipeline("PipelineTest");
		
		//Quando
		Pipeline pipelineReturn = pipelineService.create(pipeline, principal);
		
		//Entao
		assertNotNull(pipelineService.getById(pipelineReturn.getId(), principal));
		assertTrue(pipelineService.getById(pipelineReturn.getId(), principal).getName().equals(pipeline.getName()));
	
	}
	
	@Test
	public void testeAtualizarPipelineComSucesso() {
		
		//Dado
		Pipeline pipeline = montarPipeline("PipelineTest");
		Pipeline pipelineReturn = pipelineService.create(pipeline, principal);
		
		//Quando
		pipelineReturn.setName("PipelineUpdateTest");
		pipelineReturn.setDescription("DescricaoDeUpdateTest");
		pipelineReturn.setActive(false);
		
		pipelineService.update(pipelineReturn.getId(), pipelineReturn, principal);
		
		//Entao
		assertEquals(pipelineService.getById(pipelineReturn.getId(), principal).getName(), pipelineReturn.getName());
		assertEquals(pipelineService.getById(pipelineReturn.getId(), principal).getDescription(), pipelineReturn.getDescription());
		assertEquals(pipelineService.getById(pipelineReturn.getId(), principal).isActive(), pipelineReturn.isActive());
		

		
	}
	
	@Test
	public void testeDeletarPipelineComSucesso() {
		
		//Dado
		Pipeline pipelineReturn = pipelineService.create(montarPipeline("PipelineDeleteTest"), principal);
		assertNotNull(pipelineService.getById(pipelineReturn.getId(), principal));
		
		//Quando
		pipelineService.delete(pipelineReturn.getId(), principal);
		
		//Entao
		assertThrows(EntityNotFoundException.class, () -> {
			pipelineService.getById(pipelineReturn.getId(), principal);
		});
	}
	
	
	/** sessão de falha **/
	
	@Test
	public void testeErroCadastrarPipelineSemNome() {
		
		Pipeline pipeline = montarPipelineIncompleta();
		
		assertThrows(ConstraintViolationException.class, () -> {
			 pipelineService.create(pipeline, principal);
		});
		
	}
	@Test
	public void testeErroCadastrarPipelineComNomeMuitoExtenso() {
		
		String nome = "QWENamtquistnullattIntegertmalesuadattIntintenimtatarcutimperdi";
		
		assertTrue(nome.length() > 60);
		
		Pipeline pipeline = montarPipeline(nome);
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			 pipelineService.create(pipeline, principal);
		});
		
	}
	
	@Test
	public void testeErroDeletarPipelineNaoCadastrada() {
		
		assertFalse(pipelineService.getAll(pageable, principal).toList().size() > 0);
		
		assertThrows(CustomEntityNotFoundException.class, () -> {
			pipelineService.delete(1, principal);
		});
		
	}
	
	
private void apagarBanco() {
		
		Pageable pageable = Pageable.unpaged();

		List<Pipeline> pipelinesTemp = pipelineService.getAll(pageable, principal).toList();
		List<Pipeline> pipelinesERR = new ArrayList<Pipeline>();
		
		do {
			for(Pipeline p : pipelinesTemp) {
				try {
					pipelineService.delete(p.getId(), principal);
				} catch (Exception e) {
					pipelinesERR.add(p);
				}
			}
			pipelinesTemp = pipelinesERR;
			pipelinesERR = new ArrayList<Pipeline>();
		} while (pipelinesTemp.size() > 0);
		
	}
	
	private User recuperarUserPrincipal() {
		return authService.loadUserByUsername(principal.getName());
	}
	
	private Pipeline montarPipeline(String nome) {
		
		Pipeline pipeline = new Pipeline();
		pipeline.setName(nome);
		pipeline.setActive(true);
		pipeline.setUser(recuperarUserPrincipal());
		pipeline.setPDIList(List.of());
		return pipeline;
	}
	
	private Pipeline montarPipelineIncompleta() {
		
		Pipeline pipeline = new Pipeline();
		pipeline.setUser(recuperarUserPrincipal());
		pipeline.setPDIList(List.of());
		
		return pipeline;
	}
	
	//TODO Revisar a Criação em Hierarquia de PDI
	
//	private PDI montarPDI(String nome) {
//		
//		PDI pdi = new PDI();
//		pdi.setIndex(1);
//		pdi.setUser(recuperarUserPrincipal());
//		
//		return pdi;
//	}
	
	
	@AfterEach
	public void posConfig() {
		apagarBanco();
	}
	
	
}
