package steps;

import static config.ConfigInit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import pages.SideBar;

public class UtilStep extends MainSteps{
	
	@Dado("^que estou na pagina (.*)$")
	public void queEstouNaPaginaCadastroUsuario(String pagina) {
		switch (pagina) {
		case "CadastroUsuario":
			driver.get("http://localhost:3000/criar-conta");
			break;

		case "Login":

			driver.get("http://localhost:3000/login");
			break;
			
		case "Guia":

			driver.get("http://localhost:3000/guia");
			break;
			
		default:
			break;
		}
	}
	
	@Então("fazer logoff")
	public void fazerLogoff() {
		Na(SideBar.class).clickBotaoSair();
	}
	
	@Então("^devo estar na pagina( de)? (.*)$")
	public void devoEstarNaPaginaLogin(String pass,String pagina) {
		
		esperar(1);
		
		switch (pagina) {

		case "CadastroUser":

			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/criar-conta");
			break;

		case "Login":

			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/login");
			break;

		case "Guia":

			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/guia");
			break;
			
		case "Dashboard":

			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/dashboard");
			break;
			
		case "Pipeline":

			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/pipeline");
			break;
			
			
		case "HomePipeline":
			
			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/pipeline-home");
			break;
			
		case "Recuperacaodesenha":

			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/forgotten-password");
			break;
			
		case "edicao-Imagem":

			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/processar-imagem");
			break;
			
		case "edicao-Video":

			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/processar-video");
			break;
			
		case "edicao-Camera":

			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/processar-camera");
			break;
			
		case "HistoricoPipeline":
			
			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/pipeline-history");
			break;
			
		case "FluxoPipeline":
			
			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/flow");
			break;
			
		default:
			assertEquals("Tem que colocar a URL aqui jovem", ":)");
			break;
		}
	}
}
