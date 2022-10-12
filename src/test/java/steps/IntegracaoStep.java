package steps;

import static config.ConfigInit.esperar;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.camerapipeline.camera_pipeline.model.entities.input.PipelineInput;
import com.camerapipeline.camera_pipeline.model.entities.input.image.ImageData;
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

	@Dado("^que existe a conta email (.*) e senha (.*)$")
	public void queExisteAContaEmailESenha(String email, String senha) {
		System.out.println(senha);
		System.out.println(senha.replace("\"", ""));
		User novoUser = new User();
		novoUser.setEmail(email);
		novoUser.setPassword(senha.replace("\"", ""));
		novoUser.setPipelineInputs(Set.of());
		novoUser.setPipelines(Set.of());
		
		userService.create(novoUser);
	}
	
	@Então("^usuario (.*)( não)? deve estar no banco$")
	public void usuario_deve_estar_no_banco(String email,String condicao) {
		esperar(1);
		Optional<User> userRecuperado = userRepository.findByEmail(email);
		if (condicao == null) {
			assertTrue(userRecuperado.isPresent());
		} else {
			assertFalse(userRecuperado.isPresent());
		}

	}

	@Então("^remover usuario( (.*))?$")
	public void removerUsuario(String emailpassado) {
		esperar(1);
		Optional<User> userRecuperado = userRecuperado = userRepository.findByEmail(emailpassado);

		if (userRecuperado.isPresent()) {
			limparUser();
			userRepository.deleteById(userRecuperado.get().getId());
		}
	}
	
	private void limparUser() {
		if(recuperarUser().getPipelines() != null) {
			Set<Pipeline> pipelines = recuperarUser().getPipelines();
			for(Pipeline p: pipelines) {
				pipelineService.delete(p.getId(), recuperarPrincipal());
			}
		}
		
		if(recuperarUser().getPipelineInputs() != null) {
			List<ImageData> imagens = imageDataService.getAll(pageable, recuperarPrincipal()).toList();
			for(ImageData i : imagens) {
				imageDataService.deleteImage(i.getId(), recuperarPrincipal());
			}
		}
		
	}
}
