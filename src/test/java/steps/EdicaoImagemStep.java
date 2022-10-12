package steps;

import io.cucumber.java.pt.Entao;
import pages.EdicaoImagemPage;
import pages.SideBar;

import static config.ConfigInit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EdicaoImagemStep extends MainSteps {
	
	private int qtdImageData = 0 ;

	@Entao("verificar processamento salvo")
	public void verificarProcessamentoSalvo() {
		 assertEquals(qtdImageData, getAllImageDataSize());
	}

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
		qtdImageData = getAllImageDataSize();
		Na(EdicaoImagemPage.class).selecionarPipeline(value);
		qtdImageData++;
		
		esperar(1);
		assertEquals(Na(EdicaoImagemPage.class).getTituloLabel(), "Resultado:");
		
	}

	@Entao("^EI clicar no botão (.*)$")
	public void ecClicarNoBotãoVoltar(String value) {
		esperar(1);
		
		String pageAtual = Na(EdicaoImagemPage.class).getTituloLabel();
		
		switch (value) {
		case "Proximo":

			Na(EdicaoImagemPage.class).clicarBotaoProximo();
			assertEquals(Na(EdicaoImagemPage.class).getTituloLabel(), "Selecione uma pipeline");
			break;
		
		case "Voltar":
			
			Na(EdicaoImagemPage.class).clicarBotaoVoltar();
			if(pageAtual.equals("Selecione uma pipeline")) {
				assertEquals(Na(EdicaoImagemPage.class).getTituloLabel(), "Selecione o arquivo");
			}else {
				assertEquals(Na(EdicaoImagemPage.class).getTituloLabel(), "Selecione uma pipeline");
			}
			break;
		
		case "Baixar":

			Na(EdicaoImagemPage.class).clicarBotaoBaixar();
			esperar(1);
			break;

		case "Reiniciar":

			Na(EdicaoImagemPage.class).clicarBotaoReiniciar();
			assertEquals(Na(EdicaoImagemPage.class).getTituloLabel(), "Selecione o arquivo");
			break;

		default:
			break;
		}
	}



}
