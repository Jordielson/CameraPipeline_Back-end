package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuiaPage {

	@FindBy(xpath = "//*[@id=\"opt1\"]")
	private WebElement abaVisaoGeral;
	
	@FindBy(xpath = "//*[@id=\"opt2\"]")
	private WebElement abaPipelines;
	
	@FindBy(xpath = "//*[@id=\"opt3\"]")
	private WebElement abaFluxoDaPipeline;

	@FindBy(xpath = "//*[@id=\"opt4\"]")
	private WebElement abaAplicarProcessos;
	
	@FindBy(xpath = "//*[@id=\"topheader\"]/div/h4 | //*[@id=\"topheader\"]/div/h5")
	private WebElement tituloVisiom;
	
	
	public void clickAbaVisaoGeral() {
		abaVisaoGeral.click();
	}
	
	public void clickAbaPipelines() {
		abaPipelines.click();
	}
	
	public void clickabAbaFluxoDaPipeline() {
		abaFluxoDaPipeline.click();
	}
	
	public void clickAbaAplicarProcessos() {
		abaAplicarProcessos.click();
	}
	
	public String getTituloVision() {
		return tituloVisiom.getText();
	}
}
