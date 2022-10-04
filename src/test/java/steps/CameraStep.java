package steps;

import static config.ConfigInit.Na;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.CameraPage;
import pages.SideBar;

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
	
	@Então("verificar existencia camera Camera1")
	public void verificarExistenciaCameraCamera1() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Então("editar camera Camera1")
	public void editarCameraCamera1() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Então("Desabilitar camera Camera1")
	public void desabilitarCameraCamera1() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Então("excluir camera Camera1")
	public void excluirCameraCamera1() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Então("verificar inexistencia camera Camera1")
	public void verificarInexistenciaCameraCamera1() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


}
