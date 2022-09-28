package steps;

import com.camerapipeline.camera_pipeline.model.entities.user.User;

import static config.ConfigInit.*;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.LoginPage;

public class LoginStep extends MainSteps{
	
	
	@Dado("^que informei email (.*) e senha (.*)$")
	public void que_informei_email_e_senha(String email, String senha) {
		this.email = email;
		this.senha = senha;
	    Na(LoginPage.class).inserirEmailLogin(email);
	    Na(LoginPage.class).inserirSenhaLogin(senha);
	}
	
	@Quando("^tentar logar$")
	public void tentar_logar() { 
		Na(LoginPage.class).clicarBotaoEntrar();
	}


}
