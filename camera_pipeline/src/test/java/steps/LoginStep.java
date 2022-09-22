package steps;

import com.camerapipeline.camera_pipeline.model.entities.user.User;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class LoginStep {
	
	private User user;

	@Dado("que existe um usuario cadastrado com o email {string}")
	public void queExisteUmUsuarioCadastradoComOEmail(String string) {
	    
	}

	@Dado("a senha é {string}")
	public void aSenhaÉ(String string) {
	   
	}
	@Quando("logar com email {string} e senha {string}")
	public void logarComEmailESenha(String string, String string2) {
	    
	}
	@Então("o login deve ser realizado com sucesso")
	public void oLoginDeveSerRealizadoComSucesso() {
	    
	}

}
