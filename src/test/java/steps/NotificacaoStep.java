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
		 * TODO Notificações Page PDIs
		 */
			
		case "parametroRemovido_PDIPage":
			mensagemErro = "Removido!";
			break;

		case "parametrosalvo_PDIPage":
			mensagemErro = "Salvo com sucesso!";
			break;
			
		/**
		 * TODO Notificações Page Cameras
		 */

		case "CameraSalva_CameraPage":
			mensagemErro = "Salvo com sucesso!";
			break;

		case "CameraAtualizada_CameraPage":
			mensagemErro = "Atualizado com sucesso!";
			break;
			
		/**
		 * TODO Notificações Page Edição
		 */
			
		case "ArquivoNaoSelecionado_EdicaoImagemPage":
			mensagemErro = "Selecione um(a) imagem antes de prosseguir";
			break;
		
		case "ArquivoNaoSelecionado_EdicaoVideoPage":
			mensagemErro = "Selecione um(a) video antes de prosseguir";
			break;

		case "PipelineAplicadaEmCamera_EdicaoCameraPage":
			mensagemErro = "Pipeline aplicada a câmera com sucesso";
			break;

		case "ErroCameraJaProcessada_EdicaoCameraPage":
			mensagemErro = "Pipeline já foi aplicada a esta câmera";
			break;
			
		case "ErroPipelineJaAplicadaEmCamera_EdicaoCameraPage":
			mensagemErro = "Já existe uma câmera com esse pipeline";
			break;
			
		/**
		 * TODO Notificações Page Pipeline
		 */
			
		case "SucessoCriarPipeline_Pipeline":
			mensagemErro = "Salvo com sucesso!";
			break;
			
		case "SucessoSalvarPipeline_Pipeline":
			mensagemErro = "Salvo com sucesso!";
			break;

		case "PDIAdicionadoComSucesso_Pipeline":
			mensagemErro = "PDITeste adicionado com sucesso!";
			break;

		case "SucessoExcluirPipeline_Pipeline":
			mensagemErro = "Deletado com sucesso! 👌";
			break;
			
		case "ErroExcluirPipelineEmUso_Pipeline":
			mensagemErro = "Deletado com sucesso! 👌";
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
				esperado = driver.findElement(By.id("toastMsg"));
				erroRecuperado = esperado.getAttribute("innerText");
				esperar(1);
				
			} while (erroRecuperado.equals("Processando"));
			
			try {
				WebElement botaoFechar = driver.findElement(By.xpath("//*[@class=\"Toastify__close-button Toastify__close-button--light\"]"));
				botaoFechar.click();
			} catch (Exception e) {
			}
			
			assertEquals(erroRecuperado, mensagemErro);
			
		}
	}
}
