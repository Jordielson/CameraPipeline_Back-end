package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Navbar {

	@FindBy(xpath = "/html/body/div/div/nav/div/a")
	private WebElement cameraPipeline;
	
	@FindBy(xpath = "//*[@class=\"navbar navbar-expand navbar-dark\"]/span")
	private WebElement botaoGuia;
	
	public void clicarLogoCameraPipeline() {
		cameraPipeline.click();
	}
	
	public void clicarbotaoGuia() {
		botaoGuia.click();
	}
}
