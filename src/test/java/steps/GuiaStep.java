package steps;

import static config.ConfigInit.*;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.GuiaPage;

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

		case "cameras":

			Na(GuiaPage.class).clickAbaCameras();
			break;

		case "mosaico":

			Na(GuiaPage.class).clickAbaMosaicodasCameras();
			break;

		default:
			break;
		}
		

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

			textTitulo = "Pipelines";
			break;

		case "cameras":

			textTitulo = "Câmeras";
			break;

		case "mosaico":

			textTitulo = "Mosaicos das câmeras";
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
