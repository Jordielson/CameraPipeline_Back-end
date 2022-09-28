package steps;

import static config.ConfigInit.esperar;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserResquest;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;

public class IntegracaoStep extends MainSteps{

	@Dado("^que existe a conta email (.*) e senha (.*)$")
	public void queExisteAContaEmailESenha(String email, String senha) {
		this.email = email;
		this.senha = senha;
		UserResquest user = new UserResquest();
		user.setEmail(email);
		user.setPassword(senha);

		userService.create(user);
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
		Optional<User> userRecuperado;

		if (emailpassado == null) {
			userRecuperado = userRepository.findByEmail(email);
		} else {
			userRecuperado = userRepository.findByEmail(emailpassado);
		}
		if (userRecuperado.isPresent()) {
			userRepository.deleteById(userRecuperado.get().getId());
		}
	}
}
