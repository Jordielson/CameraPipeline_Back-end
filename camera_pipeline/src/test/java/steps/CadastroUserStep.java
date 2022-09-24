package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import pages.CriarContaPage;

import static config.ConfigInit.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.camerapipeline.camera_pipeline.CameraPipelineApplication;
import com.camerapipeline.camera_pipeline.model.entities.user.User;
import com.camerapipeline.camera_pipeline.model.repository.user.UserRepository;

@CucumberContextConfiguration
@SpringBootTest(classes = CameraPipelineApplication.class)
public class CadastroUserStep {

	@Autowired
	private UserRepository userRepository;
	
	private String email = "";

	@Dado("^que eu( não)? passe o email( \"(.*)\")?$")
	public void queEuPasseOEmail(String condicao, String string) {
		if (condicao == null) {
			email = string;
			Na(CriarContaPage.class).inserirEmail(string);
		}
	}

	@Dado("^(não )?passe a senha \"(.*)\"$")
	public void passeASenha(String condicao, String string) {
		
		if (condicao == null) {
			Na(CriarContaPage.class).inserirSenha(string);
		}
	}

	@Dado("^(não )?passe confirme a senha \"(.*)\"$")
	public void passeConfirmeASenha(String condicao, String string) {
		if (condicao == null) {
			Na(CriarContaPage.class).inserirConfirmacaoSenha(string);
		}
	}

	@Quando("cadastrar")
	public void cadastrar() {
		Na(CriarContaPage.class).clicarBotaoCadastrar();

	}

	@Então("a conta de usuario deve ser criada")
	public void aContaDeUsuarioDeveSerCriada() {
		WebElement esperado = null;
		do {
			esperado = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[1]/div[2]/text"));
			esperar(2);
		} while (esperado.getAttribute("innerText").equals("Processando"));

		assertEquals(esperado.getAttribute("innerText"), "Conta criada com sucesso!");
	}

	@Então("^usuario( não)? deve estar no banco$")
	public void usuario_deve_estar_no_banco(String condicao) {
		Optional<User> userRecuperado = userRepository.findByEmail(email);
		if (condicao == null) {
			assertTrue(userRecuperado.isPresent());
			userRepository.deleteById(userRecuperado.get().getId());
		} else {
			assertFalse(userRecuperado.isPresent());
		}
		
	}

	@Então("^sistema aponta erro \"(campoemail|camposenha|campoconfirmarsenha|emailexistente|conexao|outro)\"$")
	public void sistema_aponta_erro(String string) {

		WebElement esperado = null;
		String mensagemErro;
		
		switch (string) {
		case "campoemail":

			mensagemErro = "Preencha o campo de email";
			break;

		case "camposenha":

			mensagemErro = "Senha deve conter no mínimo seis dígitos";
			break;

		case "campoconfirmarsenha":

			mensagemErro = "Senha não confirmada";
			break;

		case "emailexistente":

			mensagemErro = "Email já está sendo utilizado, tente outro.";
			break;

		case "conexao":

			mensagemErro = "Network Error";
			break;
			
		case "outro":

			mensagemErro = "O loko meu, tem coisa nova aí";
			break;

		default:
			
			mensagemErro = "";
			break;
		}
	
		if(!mensagemErro.isEmpty()) {
			
			do {
				esperado = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[1]/div[2]/text"));
				esperar(2);
			} while (esperado.getAttribute("innerText").equals("Processando"));
			
			assertEquals(esperado.getAttribute("innerText"), mensagemErro);
		}

	}

}
