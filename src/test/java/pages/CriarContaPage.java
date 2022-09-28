package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CriarContaPage {

	@FindBy(xpath = "//*[@id=\"new-user\"]/div[1]/input[1]")
	private WebElement campoEmail;
	
	@FindBy(xpath = "//*[@id=\"new-user\"]/div[1]/input[2]")
	private WebElement campoSenha;
	
	@FindBy(xpath = "//*[@id=\"new-user\"]/div[1]/input[3]")
	private WebElement campoConfirmarSenha;
	
	@FindBy(xpath = "//*[@id=\"new-user\"]/div[2]/div/button[1]")
	private WebElement botaoVoltar;
	
	@FindBy(xpath = "//*[@id=\"new-user\"]/div[2]/div/button[2]")
	private WebElement botaoCadastrar;
	
	public void inserirEmail(String value) {
		campoEmail.sendKeys(value);
	}
	
	public void inserirSenha(String value) {
		campoSenha.sendKeys(value);
	}
	
	public void inserirConfirmacaoSenha(String value) {
		campoConfirmarSenha.sendKeys(value);
	}
	
	public void clicarBotaoVoltar() {
		botaoVoltar.click();
	}
	
	public void clicarBotaoCadastrar() {
		botaoCadastrar.click();
	}
	
	
}
