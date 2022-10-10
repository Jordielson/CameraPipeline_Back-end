package steps;

import static config.ConfigInit.Na;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.CameraPage;
import pages.SideBar;
import static config.ConfigInit.*;

public class CameraStep extends MainSteps{

	@Dado("que cliquei no botão AdicionarCamera")
	public void queCliqueiNoBotãoAdicionarCamera() {
		Na(CameraPage.class).clicarBotaoAdicionarNovaCamera();
	}

	@Dado("^que informei o nome da camera como (.*)$")
	public void queInformeiONomeDaCameraComo(String nome) {
	    Na(CameraPage.class).inserirCampoCardNomeCamera(nome);
	}
	
	@Dado("^que informei a URL como (.*)$")
	public void queInformeiAURLComo(String url) {
	    Na(CameraPage.class).inserirCampoCardUrl(url);
	}
	
	@Dado("^que selecionei (sim|nao) para camera privada$")
	public void queSelecioneiParaCameraPrivada(String opcao) {
		if(opcao.equals("sim")) {
			Na(CameraPage.class).clicarRadioCardPrivadaSim();
		}else {
			Na(CameraPage.class).clicarRadioCardPrivadaNao();
		}
	}
	
	@Dado("^que informei a latitude como (.*)$")
	public void queInformeiALatitude(String latitude) {
	    Na(CameraPage.class).inserirCampoCardCoordenadasLatitude(latitude);
	}
	
	@Dado("^que informei a longitude como (.*)$")
	public void queInformeiALongitude(String longitude) {
	    Na(CameraPage.class).inserirCampoCardCoordenadasLongitude(longitude);
	}
	
	@Dado("^que informei o limitadore de FPS como (.*)$")
	public void queInformeiOLimitadoreDeFPS(String fps) {
	    Na(CameraPage.class).inserirCampoCardLimitadorFPS(fps);
	}
	@Quando("clicar no botão salvar")
	public void clicarNoBotãoSalvar() {
	    Na(CameraPage.class).clicarBotaoCardSalvar();
	}
	
	@Entao("acessar aba Câmeras")
	public void acessarAbaCameras() {
		Na(SideBar.class).clickAbaCameras();
	}
	
	@Então("^verificar existencia camera (.*)$")
	public void verificarExistenciaCameraCamera1(String nome) {
		esperar(1);
	    boolean valor = Na(CameraPage.class).cameraIsPresente(nome);
	    assertTrue(valor);
	}

	@Então("^editar camera (.*)$")
	public void editarCameraCamera1(String nome) {
	    Na(CameraPage.class).clicarEditarCamera(nome);
	}
	@Então("^Desabilitar camera (.*)$")
	public void desabilitarCameraCamera1(String nome) {
	    Na(CameraPage.class).clicarAtiviarDesativarCamera(nome);
	}
	@Então("^excluir camera (.*)$")
	public void excluirCameraCamera1(String nome) {
		Na(CameraPage.class).clicarExcluirCamera(nome);
	}
	@Então("^verificar inexistencia camera (.*)$")
	public void verificarInexistenciaCameraCamera1(String nome) {
		esperar(1);
	    boolean valor = Na(CameraPage.class).cameraIsPresente(nome);
	    assertFalse(valor);
	}


}
