package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecuperacaoDeSenhaPage {
	
	@FindBy(xpath = "//*[@id=\"forgot-password\"]/div[1]/input")
	private WebElement campoEmail;

	@FindBy(xpath = "//*[@id=\"forgot-password\"]/div[2]/div/button[1]")
	private WebElement botaoVoltar;

	@FindBy(xpath = "//*[@id=\"forgot-password\"]/div[2]/div/button[2]")
	private WebElement botaoEnviar;
	
	public void inserirCampoEmail(String value) {
		campoEmail.clear();
		campoEmail.sendKeys(value);;
	}
	
	public void clicarBotaoVoltar() {
		botaoVoltar.click();
	}

	public void clicarBotaoEnviar() {
		botaoEnviar.click();
	}

}
