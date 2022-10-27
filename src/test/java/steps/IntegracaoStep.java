package steps;

import static config.ConfigInit.esperar;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.camerapipeline.camera_pipeline.model.entities.history.DataHistory;
import com.camerapipeline.camera_pipeline.model.entities.history.PdiDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.history.PipelineDataHistory;
import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Coordinate;
import com.camerapipeline.camera_pipeline.model.entities.input.image.ImageData;
import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;
import com.camerapipeline.camera_pipeline.model.entities.pdi.DigitalProcess;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ModelPDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.PDI;
import com.camerapipeline.camera_pipeline.model.entities.pdi.Parameter;
import com.camerapipeline.camera_pipeline.model.entities.pdi.ValueParameter;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.enums.ParameterType;
import com.camerapipeline.camera_pipeline.model.repository.history.PipelineDataHistoryRepository;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserResquest;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;

public class IntegracaoStep extends MainSteps{
	
	@Dado("que tenho uma Pipeline")
	public void queTenhoUmaPipeline() {
		Pipeline pipeline = new Pipeline();
		pipeline.setName("Pipeline Test");
		pipeline.setUser(recuperarUser());
		pipeline.setPDIList(List.of());
		
		pipelineService.create(pipeline, recuperarPrincipal());
	}
	
	@Dado("que tenho uma Camera")
	public void queTenhoUmaCamera() {
		Camera camera = new Camera();
		camera.setName("Camera test");
		camera.setUrl("www.testes.com.br");
		camera.setCoordinate(new Coordinate(50.00,50.00));
		camera.setFpsLimiter(90);
		camera.setIsPrivate(false);
		
		cameraService.create(camera, recuperarPrincipal());
	}
	
	@Dado("que tenho um PDI")
	public void queTenhoUmPDI() {
		
		Parameter parameter = new Parameter();
		parameter.setName("ParamTeste1");
		parameter.setIndex(1);
		parameter.setType(ParameterType.STRING);
		parameter.setRequired(false);
		
		ModelPDI mpdi = new ModelPDI();
		mpdi.setName("PDITeste");
		mpdi.setURL("www.testePDI.com");
		mpdi.setDescription("DescricaoTestePDI");
		mpdi.setParameters(List.of(parameter));
		mpdi.setPdiList(List.of());
		
		modelPDIService.create(mpdi, recuperarPrincipal());
		
	}

	@Dado("^que existe a conta email (.*) e senha (.*)$")
	public void queExisteAContaEmailESenha(String email, String senha) {
		User novoUser = new User();
		novoUser.setEmail(email);
		novoUser.setPassword(senha.replace("\"", ""));
		novoUser.setPipelineInputs(Set.of());
		novoUser.setDigitalProcess(Set.of());
		
		userService.create(novoUser);
	}
	
	@Então("^usuario (.*)( não)? deve estar no banco$")
	public void usuario_deve_estar_no_banco(String email,String condicao) {
//		esperar(1);
		Optional<User> userRecuperado = userRepository.findByEmail(email);
		if (condicao == null) {
			assertTrue(userRecuperado.isPresent());
		} else {
			assertFalse(userRecuperado.isPresent());
		}

	}

	@Então("^remover usuario( (.*))?$")
	public void removerUsuario(String emailpassado) {
//		esperar(1);
		Optional<User> userRecuperado = userRepository.findByEmail(emailpassado);

		if (userRecuperado.isPresent()) {
			limparUser();
			System.out.println(userRecuperado.get().toString());
			userRepository.deleteById(userRecuperado.get().getId());
		}
	}
	
	private void limparUser() {
		
		if(getAllCameraSize() != 0) {
			List<Camera> cameras = getAllCamera();
			ArrayList<Camera> ordCamera = new ArrayList<Camera>();
			
			for(Camera c : cameras) {
				if(c.getBaseCamera() != null) {
					ordCamera.add(0, c);
				}else {
					ordCamera.add(ordCamera.size(), c);
				}
			}
			for(Camera c : ordCamera) {
				cameraService.delete(c.getId(), recuperarPrincipal());
			}

		}
		
		if(getAllPipelineInputSize() != 0) {
			List<PipelineInput> inputs = getAllPipelineInput();
			for(PipelineInput pi : inputs) {
				pi.setPipeline(null);
			}
			User userUpdate = recuperarUser();
			userUpdate.setPipelineInputs(Set.copyOf(inputs));
			userService.update(recuperarUser().getId(), userUpdate, recuperarPrincipal());
		}
		
		 if(recuperarUser().getDigitalProcess() != null) {
		   	Set<DigitalProcess> DProcess = recuperarUser().getDigitalProcess();
		   	
		 	for(DigitalProcess p: DProcess) {
		 		if(p instanceof ModelPDI) {
		 			ModelPDI m = (ModelPDI) p;
		 			List<Parameter> parametros = m.getParameters();
		 			List<PDI> pdis = m.getPdiList();
		 			
		 			for(PDI pdi : pdis) {
		 				List<ValueParameter> valueParameters = pdi.getValueParameters();
		 				for( ValueParameter vp : valueParameters ) {
		 					valueParameterService.delete(vp.getId(), recuperarPrincipal());
		 				}
		 				pdiService.delete(pdi.getId(), recuperarPrincipal());
		 			}
		 			for(Parameter param : parametros ) {
		 				parameterService.delete(param.getId(), recuperarPrincipal());
		 			}
		 			modelPDIService.delete(p.getId(), recuperarPrincipal());
		 			
		 		}else if(p instanceof Pipeline) {
		 			int id = p.getId();
		 			int life = 1;
		 			List<PipelineDataHistory> pipelinedataHistorys = pipelineDataHistoryService.getHistoryByPipeline(pageable, id, recuperarPrincipal()).toList();
		 			System.err.println(pipelinedataHistorys.size());
		 			while(pipelinedataHistorys.size() > 0) {
		 				for(PipelineDataHistory pdh : pipelinedataHistorys) {
		 					pipelineDataHistoryRespository.deleteById(pdh.getRevision());
		 					esperar(1);
		 				}
		 				if(life == 1) {
		 					pipelineService.delete(id, recuperarPrincipal());
		 					life--;
		 					esperar(1);
		 				}
		 				pipelinedataHistorys = pipelineDataHistoryService.getHistoryByPipeline(pageable, id, recuperarPrincipal()).toList();
		 			}
		 		}
		 	}
		 }
		 
		if(recuperarUser().getPipelineInputs() != null) {
			List<ImageData> imagens = getAllImageData();
			for(ImageData i : imagens) {
				imageDataService.deleteImage(i.getId(), recuperarPrincipal());
			}
		}
		
		if(recuperarUser().getPipelineInputs() != null) {
			List<VideoData> videos = getAllVideoData();
			for(VideoData v : videos) {
				videoDataService.deleteVideo(v.getId(), recuperarPrincipal());
			}
		}
		
		
	}
}
