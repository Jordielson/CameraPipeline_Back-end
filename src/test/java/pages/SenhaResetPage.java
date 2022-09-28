package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SenhaResetPage {
	
	@FindBy(xpath = "//*[@id=\"new-user\"]/div[1]/input[1]")
	private WebElement campoSenha;
	
	@FindBy(xpath = "//*[@id=\"new-user\"]/div[1]/input[2]")
	private WebElement campoConfirmSenha;
	
	@FindBy(xpath = "//*[@id=\"new-user\"]/div[2]/div/button[1]")
	private WebElement botaoVoltar;

	@FindBy(xpath = "//*[@id=\"new-user\"]/div[2]/div/button[2]")
	private WebElement botaoAlterar;
	
	public void inserirSenha(String value) {
		campoSenha.clear();
		campoSenha.sendKeys(value);;
	}
	public void inserirConfirmSenha(String value) {
		campoConfirmSenha.clear();
		campoConfirmSenha.sendKeys(value);
	}
	public void clickBotaoVoltar() {
		botaoVoltar.click();
	}
	public void clickBotaoAlterar() {
		botaoAlterar.click();
	}
	
	
}
