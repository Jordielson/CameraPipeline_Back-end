package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlterarSenhaPage {

	@FindBy(xpath = "//*[@id=\"change-password\"]/h5/text()[2]")
	private WebElement nomeUsuario;
	
	@FindBy(xpath = "/html/body/div/div/div[1]/form/div/input[1]")
	private WebElement campoSenhaAtual;
	
	@FindBy(xpath = "/html/body/div/div/div[1]/form/div/input[2]")
	private WebElement campoNovaSenha;
	
	@FindBy(xpath = "/html/body/div/div/div[1]/form/div/input[3]")
	private WebElement campoConfirmeNovaSenha;
	
	@FindBy(xpath = "/html/body/div/div/div[1]/form/button")
	private WebElement botaoAlterar;
	
	public String getNomeUsuario() {
		return nomeUsuario.getText();
	}
	
	public void inserirSenhaAtual(String value) {
		campoSenhaAtual.sendKeys(value);
	}
	
	public void inserirNovaSenha(String value) {
		campoNovaSenha.sendKeys(value);
	}
	
	public void inserirConfirmeNovaSenha(String value) {
		campoConfirmeNovaSenha.sendKeys(value);
	}
	
	public void clickBotaoAlterar() {
		botaoAlterar.click();
	}
	
}
