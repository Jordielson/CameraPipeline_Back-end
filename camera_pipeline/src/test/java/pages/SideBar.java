package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SideBar {

	@FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/ul/li[1]/a")
	private WebElement abaHome;

	@FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/ul/li[2]/a")
	private WebElement abaEdicao;
	
	@FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/ul/li[3]/a")
	private WebElement abaPipeline;
	
	@FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/ul/li[4]/a")
	private WebElement abaCameras;
	
	@FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/ul/li[5]/a")
	private WebElement abaPDIs;
	
	@FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/ul/li[6]/a")
	private WebElement abaAlterarSenha;
	
	@FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/ul/li[7]/a")
	private WebElement abaGuia;
	
	@FindBy(xpath = "//*[@id=\"sidebar\"]/div[2]/div/a")
	private WebElement botaoSair;
	
	public void clickAbaHome() {
		abaHome.click();
	}
	public void clickAbaEdicao() {
		abaEdicao.click();
	}
	public void clickAbaPipeline() {
		abaPipeline.click();
	}
	public void clickAbaCameras() {
		abaCameras.click();
	}
	public void clickAbaPDIs() {
		abaPDIs.click();
	}
	public void clickAbaAlterarSenha() {
		abaAlterarSenha.click();
	}
	public void clickAbaGuia() {
		abaGuia.click();
	}
	public void clickBotaoSair() {
		botaoSair.click();
	}
	
}
