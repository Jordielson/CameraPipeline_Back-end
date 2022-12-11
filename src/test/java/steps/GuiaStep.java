package steps;

import static config.ConfigInit.*;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.GuiaPage;
import pages.SideBar;

public class GuiaStep {

	@Dado("^que cliquei na aba (.*)$")
	public void queCliqueiNaAbaAba(String aba) {

		switch (aba) {
		case "visão_geral":

			Na(GuiaPage.class).clickAbaVisaoGeral();
			break;

		case "pipelines":

			Na(GuiaPage.class).clickAbaPipelines();
			break;

		case "fluxoDaPipeline":

			Na(GuiaPage.class).clickabAbaFluxoDaPipeline();
			break;

		case "aplicarProcessos":

			Na(GuiaPage.class).clickAbaAplicarProcessos();
			break;

		default:
			break;
		}
		

	}
	
	@Quando("clicar logado no botão Guia")
	public void clicarLogadoNoBotãoGuia() {
		Na(SideBar.class).clickAbaGuia();
	}

	@Então("^(\"não \")?vejo o conteudo (.*)$")
	public void vejoOConteudoConteudo(String condicao, String titulo) {
		
		
		String textRecuperado = Na(GuiaPage.class).getTituloVision();
		String textTitulo = ""; 

		switch (titulo) {
		case "visão_geral":

			textTitulo = "Câmera Pipeline";
			break;

		case "pipelines":

			textTitulo = "Criação de PDI";
			break;

		case "fluxoDaPipeline":

			textTitulo = "Ediçao do Fluxo";
			break;

		case "aplicarProcessos":

			textTitulo = "Aplicando pipelines";
			break;

		default:
			break;
		}
		
		if(condicao == null) {
			assertEquals(textRecuperado, textTitulo);
		}else {
			assertFalse(textTitulo.equals(textRecuperado));
		}
		
	}

}
