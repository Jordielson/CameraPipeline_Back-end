package com.camerapipeline.camera_pipeline.unitario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.Principal;
import java.util.List;

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

import com.camerapipeline.camera_pipeline.CameraPipelineApplication;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
import com.camerapipeline.camera_pipeline.model.enums.Category;
import com.camerapipeline.camera_pipeline.model.enums.ParameterType;
import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;
import com.camerapipeline.camera_pipeline.provider.exception.CustomEntityNotFoundException;
import com.camerapipeline.camera_pipeline.provider.services.auth.AuthService;
import com.camerapipeline.camera_pipeline.provider.services.pdi.ModelPDIService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration
@SpringBootTest(classes = CameraPipelineApplication.class)
public class TesteModelPDI {

	@Autowired
    private AuthService authService;
	
	
	@Autowired
	private ModelPDIService modelPDIService;
	
	Pageable pageable = Pageable.unpaged();
	
	private Principal principal = null;

	
	@BeforeEach
	public void preConfig() {
		
		principal = authService.authenticateUser("admin@admin.com", "123456");
		apagarBanco();
	}
	
	/** sessão de sucesso **/
	
	@Test
	public void testeCriarPDIComSucesso() {
		
		//Dado
		ModelPDI modelPDI = montarModelPDI("modelPDITest1");
		modelPDI.setParameters(List.of(montarParameter("ParametroTest1",1)));
		
		//Quando
		ModelPDI modelPDIReturn = modelPDIService.create(modelPDI, principal);
		
		//Entao
		assertNotNull(modelPDIService.getById(modelPDIReturn.getId(), principal));
		assertTrue(modelPDIService.getById(modelPDIReturn.getId(), principal).getName().equals(modelPDI.getName()));
		assertEquals(
				modelPDIService.getById(modelPDIReturn.getId(), principal).getParameters().get(0).getName(),
				modelPDI.getParameters().get(0).getName());
	}
	
	@Test
	public void testeAtualizarPDIComSucesso() {
		
		//Dado
		ModelPDI modelPDI = montarModelPDI("modelPDITest1");
		modelPDI.setParameters(List.of(montarParameter("ParametroTest1",1)));
		ModelPDI modelPDIReturn = modelPDIService.create(modelPDI, principal);
	
		//Quando
		Parameter parameter1 = montarParameter("ParametroTest1",1);
		Parameter parameter2 = montarParameter("ParametroTest2",2);
		
		modelPDIReturn.setName("modelPDITestUpdate");
		modelPDIReturn.setParameters(List.of(parameter1, parameter2));
		
		modelPDIService.update(modelPDIReturn.getId(), modelPDIReturn, principal);
		
		//Entao
		assertEquals(modelPDIService.getById(modelPDIReturn.getId(), principal).getName(), modelPDIReturn.getName());
		assertEquals(modelPDIService.getById(modelPDIReturn.getId(), principal).getParameters().size(), 2);
		
	}
	
	@Test
	public void testeDeletarPDIComSucesso() {
		
		//Dado
		ModelPDI modelPDI = montarModelPDI("modelPDITest1");
		modelPDI.setParameters(List.of(montarParameter("ParametroTest1",1)));
		ModelPDI modelPDIReturn = modelPDIService.create(modelPDI, principal);
	
		assertNotNull(modelPDIService.getById(modelPDIReturn.getId(), principal));
		
		//Quando
		modelPDIService.delete(modelPDIReturn.getId(), principal);
		
		//Entao
		assertThrows(EntityNotFoundException.class, () -> {
			modelPDIService.getById(modelPDIReturn.getId(), principal);
		});
		
	}
	
	/** sessão de falha **/
	
	@Test
	public void testeErroCadastrarModelPDIComNomeExistente() {
		
		ModelPDI modelPDI = montarModelPDI("ModelPDITest");
		modelPDI.setParameters(List.of(montarParameter("ParametroTest1",1)));
		ModelPDI modelPDIReturn = modelPDIService.create(modelPDI, principal);
		
		assertThrows(BusinessException.class, () -> {
			modelPDIService.create(modelPDI, principal);
		});
		
		modelPDIService.delete(modelPDIReturn.getId(), principal);
	}
	
	@Test
	public void testeErroSalvarModelPDISemParametros() {
		
		assertThrows(NullPointerException.class, () -> {
			modelPDIService.create(montarModelPDI("ModelPDITest"), principal);
		});
		
	}
	
	@Test
	public void testeErroDeletarCameraNaoCadastrada() {
		
		assertFalse(modelPDIService.getAll(pageable, principal).toList().size() > 0);
		
		assertThrows(CustomEntityNotFoundException.class, () -> {
			modelPDIService.delete(1, principal);
		});
		
	}
	
	
	private void apagarBanco() {
		
		Pageable pageable = Pageable.unpaged();

		List<ModelPDI> modelPDIsTemp = modelPDIService.getAll(pageable, principal).toList();
		
		for(ModelPDI p : modelPDIsTemp) {
			modelPDIService.delete(p.getId(), principal);
		}
		
	}
	
	private ModelPDI montarModelPDI(String nome) {
		ModelPDI modelPDI = new ModelPDI();
		modelPDI.setName(nome);
		modelPDI.setURL("www.modelPDITest1.com.br");
		return modelPDI;
	}
	
	private Parameter montarParameter(String nome, int index) {
		Parameter parameter = new Parameter();
		parameter.setName(nome);
		parameter.setIndex(index);
		parameter.setType(ParameterType.STRING);
		parameter.setRequired(false);
		
		return parameter;
	}
	
	@AfterEach
	public void posConfig() {
		apagarBanco();
	}
	
}
