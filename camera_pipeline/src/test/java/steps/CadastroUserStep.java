package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.spring.CucumberContextConfiguration;
import pages.CriarContaPage;
import pages.LoginPage;
import pages.Navbar;

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
import com.camerapipeline.camera_pipeline.presentation.dto.user.UserResquest;
import com.camerapipeline.camera_pipeline.provider.services.user.UserService;

@CucumberContextConfiguration
@SpringBootTest(classes = CameraPipelineApplication.class)
public class CadastroUserStep {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	private String email = "";
	private String senha;

	@Dado("^que eu(\" não\")? passe o email( (.*))?$")
	public void queEuPasseOEmail(String condicao, String string) {
		if (condicao == null) {
			email = string;
			Na(CriarContaPage.class).inserirEmail(string);
		}
	}

	@Dado("^(não )?passe a senha \"(.*)\"$")
	public void passeASenha(String condicao, String string) {

		if (condicao == null) {
			senha = string;
			Na(CriarContaPage.class).inserirSenha(string);
		}
	}

	@Dado("^(não )?passe confirme a senha( \"(.*)\")?$")
	public void passeConfirmeASenha(String condicao, String string) {
		if (condicao == null) {
			Na(CriarContaPage.class).inserirConfirmacaoSenha(string);
		}
	}

	@Dado("que o email já está cadastrado")
	public void queOEmailJáEstáCadastrado() {
		if (!email.isEmpty()) {
			UserResquest user = new UserResquest();
			user.setEmail(email);
			user.setPassword(senha);

			userService.create(user);

		}
	}

	@Dado("^que estou na pagina (CadastroUser|Login)$")
	public void queEstouNaPaginaCadastroUsuario(String pagina) {
		switch (pagina) {
		case "CadastroUsuario":
			driver.get("http://localhost:3000/criar-conta");
			break;

		case "Login":

			driver.get("http://localhost:3000/login");
			break;
		default:
			break;
		}
	}

	@Quando("^clicar no botão (.*)$")
	public void clicarNoBotão(String botao) {
		switch (botao) {
		
		case "voltar":

			Na(CriarContaPage.class).clicarBotaoVoltar();
			break;
		
		case "CameraPipeline":
			
			Na(Navbar.class).clicarLogoCameraPipeline();;
			break;
		
		case "Cadastrar":

			Na(LoginPage.class).clicarBotaoCadastrar();;
			break;
		
		case "Guia":

			Na(Navbar.class).clicarbotaoGuia();;
			break;
		
		default:
			break;
		}
		
		esperar(2);
	}

	@Quando("cadastrar")
	public void cadastrar() {
		Na(CriarContaPage.class).clicarBotaoCadastrar();

	}

	@Então("^devo estar na pagina (.*)$")
	public void devoEstarNaPaginaLogin(String pagina) {

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
			
		default:
			break;
		}
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
		} else {
			assertFalse(userRecuperado.isPresent());
		}

	}

	@Então("^remover usuario( (.*))?$")
	public void removerUsuario(String emailpassado) {
		Optional<User> userRecuperado;

		if (emailpassado == null) {
			userRecuperado = userRepository.findByEmail(email);
		} else {
			userRecuperado = userRepository.findByEmail(emailpassado);
		}
		if (userRecuperado.isPresent()) {
			userRepository.deleteById(userRecuperado.get().getId());
		}
	}

	@Então("^sistema aponta erro (campoemail|formatocampoemail|camposenha|campoconfirmarsenha|emailexistente|conexao|outro)$")
	public void sistema_aponta_erro(String string) {

		WebElement esperado = null;
		String mensagemErro;
		String erroRecuperado;

		switch (string) {
		case "campoemail":

			mensagemErro = "Preencha o campo de email";
			break;

		case "formatocampoemail":

			mensagemErro = "Formato de email inválido.";
			break;

		case "camposenha":

			mensagemErro = "Senha deve conter no mínimo seis dígitos";
			break;

		case "campoconfirmarsenha":

			mensagemErro = "Senha não confirmada.";
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

		if (!mensagemErro.isEmpty()) {

			do {
				esperado = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[1]/div[2]/text"));
				erroRecuperado = esperado.getAttribute("innerText");
				esperar(1);
			} while (erroRecuperado.equals("Processando"));

			assertEquals(erroRecuperado, mensagemErro);
			WebElement botaoFechar = driver.findElement(By.xpath("/html/body/div/div/div/div/div/button"));
			botaoFechar.click();
		}

	}

}
