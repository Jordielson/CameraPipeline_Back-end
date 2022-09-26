package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import pages.CriarContaPage;

import static config.ConfigInit.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.apache.tomcat.jni.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.camerapipeline.camera_pipeline.model.repository.user.UserRepository;


public class CadastroUserStep {
	
	private UserRepository userRepository;
	
	@Dado("que eu passe o email {string}")
	public void queEuPasseOEmail(String string) {
//	    Na(CriarContaPage.class).inserirEmail(string);
	}

	@Dado("passe a senha {string}")
	public void passeASenha(String string) {
//	    Na(CriarContaPage.class).inserirSenha(string);
	}
	
	@Dado("passe confirme a senha {string}")
	public void passeConfirmeASenha(String string) {
//		Na(CriarContaPage.class).inserirConfirmacaoSenha(string);
	}
	
	@Quando("cadastrar")
	public void cadastrar() {
	    Na(CriarContaPage.class).clicarBotaoCadastrar();
	   
	}
	@Então("a conta de usuario deve ser criada")
	public void aContaDeUsuarioDeveSerCriada() {

		WebElement esperado = driver.findElement(By.id("teste"));	
		assertEquals(esperado.getAttribute("toastMsg"), "Preencha o campo de email");
			
	}


}
