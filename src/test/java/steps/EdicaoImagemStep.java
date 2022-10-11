package steps;

import io.cucumber.java.pt.Entao;
import pages.EdicaoImagemPage;
import pages.SideBar;

import static config.ConfigInit.*;

public class EdicaoImagemStep extends MainSteps {

	@Entao("acessar aba Edição-Camera")
	public void acessarAbaEdiçãoCamera() {
		Na(SideBar.class).clickAbaEdicaoImagem();
	}

	@Entao("passar URL de conteudo {string}")
	public void passarURLDeConteudo(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Entao("^passar file \"(.*)\"$")
	public void passarFile(String value) {
		Na(EdicaoImagemPage.class).inserirFile(value);
	}

	@Entao("^selecionar Pipeline \"(.*)\"$")
	public void selecionarPipeline(String value) {
		Na(EdicaoImagemPage.class).selecionarPipeline(value);
	}

	@Entao("^EI clicar no botão (.*)$")
	public void ecClicarNoBotãoVoltar(String value) {
		esperar(1);
		switch (value) {
		case "Proximo":

			Na(EdicaoImagemPage.class).clicarBotaoProximo();
			break;
		
		case "Voltar":

			Na(EdicaoImagemPage.class).clicarBotaoVoltar();
			break;
		
		case "Baixar":

			Na(EdicaoImagemPage.class).clicarBotaoBaixar();
			break;

		case "Reiniciar":

			Na(EdicaoImagemPage.class).clicarBotaoReiniciar();
			break;

		default:
			break;
		}
	}



}
