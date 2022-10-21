package steps;

import static config.ConfigInit.esperar;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Camera;
import com.camerapipeline.camera_pipeline.model.entities.input.camera.Coordinate;
import com.camerapipeline.camera_pipeline.model.entities.input.image.ImageData;
import com.camerapipeline.camera_pipeline.model.entities.input.video.VideoData;
import com.camerapipeline.camera_pipeline.model.entities.pipeline.Pipeline;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
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

	@Dado("^que existe a conta email (.*) e senha (.*)$")
	public void queExisteAContaEmailESenha(String email, String senha) {
		User novoUser = new User();
		novoUser.setEmail(email);
		novoUser.setPassword(senha.replace("\"", ""));
		novoUser.setPipelineInputs(Set.of());
		// TODO user agora possui DigitalProcess que eh a classe pai do Pipeline
		// novoUser.setPipelines(Set.of());
		
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
		
		// TODO user agora possui DigitalProcess que eh a classe pai do Pipeline
		// if(recuperarUser().getPipelines() != null) {
		//   	Set<Pipeline> pipelines = recuperarUser().getPipelines();
		// 	for(Pipeline p: pipelines) {
		// 		pipelineService.delete(p.getId(), recuperarPrincipal());
		// 	}
		// }
		
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
