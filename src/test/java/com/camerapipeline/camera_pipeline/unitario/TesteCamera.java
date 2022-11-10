package com.camerapipeline.camera_pipeline.unitario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.TransactionSystemException;

import com.camerapipeline.camera_pipeline.CameraPipelineApplication;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Coordinate;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;
import com.camerapipeline.camera_pipeline.provider.services.input.camera.CameraService;
import com.camerapipeline.camera_pipeline.provider.services.pipeline.PipelineService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration
@SpringBootTest(classes = CameraPipelineApplication.class)
public class TesteCamera {
	
	@Autowired
    private AuthService authService;
	
	@Autowired
	private PipelineService pipelineService;
	
	@Autowired
	private CameraService cameraService;
	
	Pageable pageable = Pageable.unpaged();
	
	private Principal principal = null;
	
	private Coordinate coordinate = new Coordinate(50.00,50.00);
	
	private List<Pipeline> pipelines = new ArrayList<Pipeline>();
	
	@BeforeEach
	public void preConfig() {
		
		principal = authService.authenticateUser("admin@admin.com", "123456");
		apagarBanco();
	}
	
	
	/** sessão de sucesso **/
	
	@Test
	public void testeCriarCameraComSucesso() {
		
		//Dado
		Camera camera = montarCamera("Camera1");
		
		//Quando
		Camera cameraReturn = cameraService.create(camera, principal);
		
		//Entao
		assertNotNull(cameraService.getById(cameraReturn.getId(), principal));
		assertTrue(cameraService.getById(camera.getId()).getName().equals(camera.getName()));
		
		cameraService.delete(cameraReturn.getId(), principal);
		
	}
	
	@Test
	public void testeAtualizarCameraComSucesso() {
		
		//Dado
		Camera camera = cameraService.create(montarCamera("Camera1"), principal);
		Camera cameraReturn =  cameraService.getById(camera.getId(), principal);
		Pipeline pipeline1 = montarPipeline("Pipeline1");
		pipelines.add(pipelineService.create(pipeline1, principal));
		
		//Quando
		coordinate.setLatitude(-90.00);
		coordinate.setLongitude(90.00);
		cameraReturn.setCoordinate(coordinate);
		cameraReturn.setIsPrivate(false);
		cameraReturn.setFpsLimiter(30);
		cameraReturn.setUrl("www.UpdateTeste.com.br");
		cameraReturn.setName("CameraUpdate");
		cameraService.update(cameraReturn.getId(), cameraReturn, principal);

		pipelineService.update(pipelines.get(0).getId(), pipelines.get(0), principal);
		
		//Entao
		assertEquals(cameraService.getById(camera.getId()).getCoordinate(), cameraReturn.getCoordinate());
		assertEquals(cameraService.getById(camera.getId()).getFpsLimiter(), cameraReturn.getFpsLimiter());
		assertEquals(cameraService.getById(camera.getId()).getIsPrivate(), cameraReturn.getIsPrivate());
		assertEquals(cameraService.getById(camera.getId()).getName(), cameraReturn.getName());
		assertEquals(cameraService.getById(camera.getId()).getUrl(), cameraReturn.getUrl());
		
	}
	
	@Test
	public void testeDeletarCameraComSucesso() {
		
		//Dado
		Camera camera = cameraService.create(montarCamera("Camera1"), principal);
		assertNotNull(camera);
		
		//Quando
		cameraService.delete(camera.getId(), principal);
		
		//Entao
		assertThrows(EntityNotFoundException.class, () -> {
			cameraService.getById(camera.getId(), principal);
		});
		
	}
	
	
	/** sessão de falha **/
	
	@Test
	public void testeErroCadastrarCameraComNomeExistente() {
		
		Camera camera = cameraService.create(montarCamera("Camera1"), principal);
		
		assertThrows(BusinessException.class, () -> {
			cameraService.create(montarCamera("Camera1"), principal);
		});
		
		cameraService.delete(camera.getId(), principal);
	}
	
	@Test
	public void testeErroLimitesCoordenadas() {
		
		Camera camera = cameraService.create(montarCamera("Camera1"), principal);
		coordinate.setLatitude(-91.00);
		coordinate.setLongitude(91.00);
		camera.setCoordinate(coordinate);
		
		assertThrows(TransactionSystemException.class, () -> {
			cameraService.update(camera.getId(), camera, principal);
		});
		
		cameraService.delete(camera.getId(), principal);
	}
	
	@Test
	public void testeErroDeletarCameraNaoCadastrada() {
		
		assertFalse(cameraService.getAll(pageable, principal).toList().size() > 0);
		
		assertThrows(CustomEntityNotFoundException.class, () -> {
			cameraService.delete(UUID.fromString("047193b9-c675-4e7d-af5e-1c7ef1246102"), principal);
		});
		
	}
	
	
	
	
	private void apagarBanco() {
		
		Pageable pageable = Pageable.unpaged();

		List<Camera> camerasTemp = cameraService.getAll(pageable, principal).toList();
		
		for(Camera c : camerasTemp) {

			cameraService.delete(c.getId(), principal);
		}
		
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
	
	private Camera montarCamera(String nome) {
		
		Camera camera = new Camera();
		camera.setName(nome);
		camera.setUrl("www.testes.com.br");
		camera.setCoordinate(coordinate);
		camera.setFpsLimiter(90);
		camera.setIsActive(true);
		camera.setIsPrivate(false);
		camera.setUser(recuperarUserPrincipal());
		
		return camera;
	}
	
	@AfterEach
	public void posConfig() {
		apagarBanco();
	}

}
