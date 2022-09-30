package steps;

import static config.ConfigInit.Na;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.LoginPage;
import pages.RecuperacaoDeSenhaPage;
import pages.SenhaResetPage;

public class RecuperarSenhaStep extends MainSteps {

	@Dado("^que informo o emailrecuperacaosenha (.*)$")
	public void queInformoOEmailUserteste1UserCom(String email) {
		this.email = email;
		Na(RecuperacaoDeSenhaPage.class).inserirCampoEmail(email);
	}

	@Quando("clicar em RecuperarSenha")
	public void clicarEmRecuperarSenha() {
		Na(LoginPage.class).clicarBotaoRecuperarSenha();
	}
	
	@Dado("que usuario solicita recuperação de senha")
	public void queUsuarioSolicitaRecuperaçãoDeSenha() {
	    
	}
	
	@Dado("que informo a novasenharecuperacao {int} e confirmo")
	public void queInformoANovasenharecuperacaoEConfirmo(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


	@Quando("acessar o link enviado por email")
	public void acessarOLinkEnviadoPorEmail() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
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
