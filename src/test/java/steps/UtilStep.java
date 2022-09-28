package steps;

import static config.ConfigInit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;

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
			
		case "Pipeline":

			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/pipeline");
			break;
			
		case "Recuperacaodesenha":

			assertEquals(driver.getCurrentUrl(), "http://localhost:3000/forgotten-password");
			break;
			
		default:
			assertEquals("Tem que colocar a URL aqui jovem", ":)");
			break;
		}
	}
}
