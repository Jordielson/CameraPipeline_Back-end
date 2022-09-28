package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	@FindBy(xpath = "//*[@id=\"formBasicEmail\"]")
	private WebElement campoEmailLogin;
	
	@FindBy(xpath = "//*[@id=\"formBasicPassword\"]")
	private WebElement campoSenhaLogin;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/form/div[3]/div/button[2]")
	private WebElement botaoCadastrar;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/form/div[3]/div/button[1]")
	private WebElement botaoEntrar;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/form/div[4]/div/a")
	private WebElement botaoRecuperarSenha;

	public void inserirEmailLogin(String value) {
		campoEmailLogin.clear();
		campoEmailLogin.sendKeys(value);
	}
	
	public void inserirSenhaLogin(String value) {
		campoSenhaLogin.clear();
		campoSenhaLogin.sendKeys(value);
	}
	
	public void clicarBotaoEntrar() {
		botaoEntrar.click();
	}

	public void clicarBotaoCadastrar() {
		botaoCadastrar.click();
	}
	
	public void clicarBotaoRecuperarSenha() {
		botaoRecuperarSenha.click();
	}
	
}
