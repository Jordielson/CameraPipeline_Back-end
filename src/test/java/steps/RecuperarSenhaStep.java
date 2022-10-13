package steps;

import static config.ConfigInit.Na;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.LoginPage;
import pages.RecuperacaoDeSenhaPage;
import pages.SenhaResetPage;
import static config.ConfigInit.*;

public class RecuperarSenhaStep extends MainSteps {
	
	String link = null;

	@Dado("^que informo o emailrecuperacaosenha (.*)$")
	public void queInformoOEmail(String email) {
		Na(RecuperacaoDeSenhaPage.class).inserirCampoEmail(email);
	}

	@Quando("clicar em RecuperarSenha")
	public void clicarEmRecuperarSenha() {
		Na(LoginPage.class).clicarBotaoRecuperarSenha();
	}
	
	@Dado("que usuario solicita recuperação de senha")
	public void queUsuarioSolicitaRecuperaçãoDeSenha() {
		link = userService.forgotPassword("userteste1@user.com", "http://localhost:3000/password-reset");
	    
	}
	
	@Dado("^que informo a novasenharecuperacao (.*) e confirmo (.*)$")
	public void queInformoANovasenharecuperacaoEConfirmo(String senha , String confirmacaoDeSenha) {
	    Na(SenhaResetPage.class).inserirSenha(senha);
	    Na(SenhaResetPage.class).inserirConfirmSenha(confirmacaoDeSenha);
	}


	@Quando("acessar o link enviado por email")
	public void acessarOLinkEnviadoPorEmail() {
	    alterarJanela(link);
	}
	

	@Quando("^RCS clicar em (.*)$")
	public void clicarEm(String value) {

		switch (value) {
		case "Voltar":

			Na(RecuperacaoDeSenhaPage.class).clicarBotaoVoltar();
			break;

		case "Enviar":

			Na(RecuperacaoDeSenhaPage.class).clicarBotaoEnviar();
			break;

		case "SR-alterar":

			Na(SenhaResetPage.class).clickBotaoAlterar();
			break;

		case "SR-voltar":
			
			Na(SenhaResetPage.class).clickBotaoVoltar();
			break;

		default:
			break;
		}

	}

}
