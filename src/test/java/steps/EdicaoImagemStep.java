package steps;

import io.cucumber.java.pt.Entao;
import pages.EdicaoImagemPage;
import pages.SideBar;

import static config.ConfigInit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

public class EdicaoImagemStep extends MainSteps {

	private int qtdImageData = 0;

	@Entao("EI verificar processamento salvo")
	public void verificarProcessamentoSalvo() {
		assertEquals(qtdImageData, getAllImageDataSize());
	}

	@Entao("acessar aba Edição-Camera")
	public void acessarAbaEdiçãoCamera() {
		Na(SideBar.class).clickAbaEdicaoImagem();
	}

	@Entao("EI passar URL de conteudo {string}")
	public void passarURLDeConteudo(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Entao("^EI passar file \"(.*)\"$")
	public void passarFile(String value) {
		File file = new File(value);
		Na(EdicaoImagemPage.class).inserirFile(file.getAbsolutePath());
	}

	@Entao("^EI selecionar Pipeline \"(.*)\"$")
	public void selecionarPipeline(String value) {
		qtdImageData = getAllImageDataSize();
		Na(EdicaoImagemPage.class).selecionarPipeline(value);
		qtdImageData++;

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
			esperar(1);
			break;

		case "Reiniciar":

			Na(EdicaoImagemPage.class).clicarBotaoReiniciar();
			break;

		default:
			break;
		}
	}

	@Entao("^EI devo estar na (.*) etapa$")
	public void eiDevoEstarNaEtapa(String value) {
		esperar(1);

		switch (value) {
		case "primeira":

			assertEquals(Na(EdicaoImagemPage.class).getTituloLabel(), "Selecione o arquivo");
			break;

		case "segunda":

			assertEquals(Na(EdicaoImagemPage.class).getTituloLabel(), "Selecione uma pipeline");
			break;

		case "terceira":

			assertEquals(Na(EdicaoImagemPage.class).getTituloLabel(), "Resultado:");
			break;

		default:
			break;
		}
	}

}
