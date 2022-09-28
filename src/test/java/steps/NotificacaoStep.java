package steps;

import static config.ConfigInit.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.cucumber.java.pt.Então;

public class NotificacaoStep extends MainSteps{

	@Então("^sistema notifica (.*)$")
	public void sistema_notifica(String string) {

		WebElement esperado = null;
		String mensagemErro;
		String erroRecuperado;

		switch (string) {
		
		
		/**
		 * TODO Notificações Login
		 */
		
		case "form_login":
			mensagemErro = "Email ou senha inválida";
			break;

		/**
		 * TODO Notificações Cadastro de Usuario
		 */

		case "campoemail_Cadastrousuario":
			mensagemErro = "Preencha o campo de email";
			break;

		case "formatocampoemail_Cadastrousuario":
			mensagemErro = "Formato de email inválido.";
			break;

		case "camposenha_Cadastrousuario":
			mensagemErro = "Senha deve conter no mínimo seis dígitos";
			break;

		case "campoconfirmarsenha_Cadastrousuario":
			mensagemErro = "Senha não confirmada.";
			break;

		case "emailexistente_Cadastrousuario":
			mensagemErro = "Email já está sendo utilizado, tente outro.";
			break;

		case "contacriada_Cadastrousuario":
			mensagemErro = "Conta criada com sucesso!";
			break;

		/**
		 * TODO Notificações Alterar Senha
		 */

		case "senhaalterada_Alterarsenha":
			mensagemErro = "Senha alterada com sucesso!";
			break;

		case "camposenhaatual_Alterarsenha":
			mensagemErro = "Digite sua senha atual.";
			break;
			
		case "senhaincorreta_Alterarsenha":
			mensagemErro = "Senha inválida";
			break;

		case "camponovasenha_Alterarsenha":
			mensagemErro = "Sua nova senha deve conter no mínimo seis dígitos.";
			break;
		
		case "campoconfirmarsenha_Alterarsenha":
			mensagemErro = "Senha não confirmada.";
			break;
			
		case "espacosnovasenha_Alterarsenha":
			mensagemErro = "Senha não pode conter caracteres vazios.";
			break;

		case "errotentativa_Alterarsenha":
			mensagemErro = "Ocorreu um erro tentar alterar sua senha.";
			break;
		
		case "errosenhaigual_Alterarsenha":
			mensagemErro = "Sua nova senha não pode ser igual a anterior.";
			break;
			
			
		/**
		 * TODO Notificações Recuperação de Senha
		 */
			
		case "EmailRecuperacao_Enviado":
			mensagemErro = "Foi enviado o link de recuperação para seu email";
			break;
		
		/**
		 * TODO Notificações Senha Reset
		 */
			
		case "senhaalterada_senhareset":
			mensagemErro = "Senha alterada!!";
			break;
			
		/**
		 * TODO Notificações Gerais
		 */
			
		case "campoemail_Geral":
			mensagemErro = "Preencha o campo de email";
			break;
		
		case "formatocampoemail_Geral":
			mensagemErro = "Formato de email inválido.";
			break;

		case "conexao":
			mensagemErro = "Network Error";
			break;

		case "outro":
			mensagemErro = "Oh loko meu, tem coisa nova aí";
			break;

		default:
			mensagemErro = "";
			break;
		}

		if (!mensagemErro.isEmpty()) {

			do {
				esperar(1);
				esperado = driver.findElement(By.xpath("//*[@class=\"Toastify\"]/div/div/div[1]/div[2]/text"));
				erroRecuperado = esperado.getAttribute("innerText");
				esperar(1);
			} while (erroRecuperado.equals("Processando"));

			WebElement botaoFechar = driver.findElement(By.xpath("//*[@class=\"Toastify\"]/div/div/button"));
			botaoFechar.click();
			assertEquals(erroRecuperado, mensagemErro);
		}

	}
}
