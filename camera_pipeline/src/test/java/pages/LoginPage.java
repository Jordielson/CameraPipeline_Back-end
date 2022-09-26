package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/form/div[3]/div/button[2]")
	private WebElement botaoCadastrar;

	public void clicarBotaoCadastrar() {
		botaoCadastrar.click();
	}
}
