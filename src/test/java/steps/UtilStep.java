package steps;

import static config.ConfigInit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;

public class UtilStep extends MainSteps{
	
	@Então("^devo estar na pagina (.*)$")
	public void devoEstarNaPaginaLogin(String pagina) {
		
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
			
		default:
			assertEquals("Tem que colocar a URL aqui jovem", ":)");
			break;
		}
	}
}
