package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.CriarContaPage;
import pages.LoginPage;
import pages.Navbar;

import static config.ConfigInit.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;


import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserResquest;

public class CadastroUserStep extends MainSteps {

	@Dado("^que eu(\" não\")? passe o email( (.*))?$")
	public void queEuPasseOEmail(String condicao, String string) {
		if (condicao == null) {
			this.email = string;
			Na(CriarContaPage.class).inserirEmail(string);
		}
	}

	@Dado("^(não )?passe a senha \"(.*)\"$")
	public void passeASenha(String condicao, String string) {

		if (condicao == null) {
			this.senha = string;
			Na(CriarContaPage.class).inserirSenha(string);
		}
	}

	@Dado("^(não )?passe confirme a senha( \"(.*)\")?$")
	public void passeConfirmeASenha(String condicao, String string) {
		if (condicao == null) {
			Na(CriarContaPage.class).inserirConfirmacaoSenha(string);
		}
	}

	@Dado("que o email já está cadastrado")
	public void queOEmailJáEstáCadastrado() {
		if (!email.isEmpty()) {
			UserResquest user = new UserResquest();
			user.setEmail(email);
			user.setPassword(senha);

			userService.create(user);

		}
	}

	@Dado("^que estou na pagina (CadastroUser|Login)$")
	public void queEstouNaPaginaCadastroUsuario(String pagina) {
		switch (pagina) {
		case "CadastroUsuario":
			driver.get("http://localhost:3000/criar-conta");
			break;

		case "Login":

			driver.get("http://localhost:3000/login");
			break;
		default:
			break;
		}
	}

	@Quando("^clicar no botão (.*)$")
	public void clicarNoBotão(String botao) {
		switch (botao) {
		
		case "voltar":

			Na(CriarContaPage.class).clicarBotaoVoltar();
			break;
		
		case "CameraPipeline":
			
			Na(Navbar.class).clicarLogoCameraPipeline();;
			break;
		
		case "Cadastrar":

			Na(LoginPage.class).clicarBotaoCadastrar();;
			break;
		
		case "Guia":

			Na(Navbar.class).clicarbotaoGuia();;
			break;
		
		default:
			break;
		}
		
		esperar(2);
	}

	@Quando("cadastrar")
	public void cadastrar() {
		Na(CriarContaPage.class).clicarBotaoCadastrar();

	}


}
