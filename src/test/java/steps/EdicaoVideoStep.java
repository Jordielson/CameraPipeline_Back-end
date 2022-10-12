package steps;

import io.cucumber.java.pt.Entao;
import pages.EdicaoVideoPage;
import pages.SideBar;
import static config.ConfigInit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

public class EdicaoVideoStep extends MainSteps{
	
	private int qtdVideoData = 0;

	@Entao("EV verificar processamento salvo")
	public void verificarProcessamentoSalvo() {
		assertEquals(qtdVideoData, getAllVideoDataSize());
	}

	@Entao("acessar aba Edição-Video")
	public void acessarAbaEdiçãoCamera() {
		Na(SideBar.class).clickAbaEdicaoVideo();;
	}

	@Entao("EV passar URL de conteudo {string}")
	public void passarURLDeConteudo(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Entao("^EV passar file \"(.*)\"$")
	public void passarFile(String value) {
		File file = new File(value);
		Na(EdicaoVideoPage.class).inserirFile(file.getAbsolutePath());
	}

	@Entao("^EV selecionar Pipeline \"(.*)\"$")
	public void selecionarPipeline(String value) {
		qtdVideoData = getAllVideoDataSize();
		Na(EdicaoVideoPage.class).selecionarPipeline(value);
		qtdVideoData++;

	}

	@Entao("^EV clicar no botão (.*)$")
	public void ecClicarNoBotãoVoltar(String value) {
		esperar(1);

		switch (value) {
		case "Proximo":

			Na(EdicaoVideoPage.class).clicarBotaoProximo();
			break;

		case "Voltar":

			Na(EdicaoVideoPage.class).clicarBotaoVoltar();
			break;

		case "Baixar":

			Na(EdicaoVideoPage.class).clicarBotaoBaixar();
			esperar(1);
			break;

		case "Reiniciar":

			Na(EdicaoVideoPage.class).clicarBotaoReiniciar();
			break;

		default:
			break;
		}
	}

	@Entao("^EV devo estar na (.*) etapa$")
	public void eiDevoEstarNaEtapa(String value) {
		esperar(1);

		switch (value) {
		case "primeira":

			assertEquals(Na(EdicaoVideoPage.class).getTituloLabel(), "Selecione o arquivo");
			break;

		case "segunda":

			assertEquals(Na(EdicaoVideoPage.class).getTituloLabel(), "Selecione uma pipeline");
			break;

		case "terceira":

			assertEquals(Na(EdicaoVideoPage.class).getTituloLabel(), "Resultado:");
			break;

		default:
			break;
		}
	}
}
