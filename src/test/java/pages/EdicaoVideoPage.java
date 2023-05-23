package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EdicaoVideoPage {

	@FindBy(xpath = "//*[@class=\"image_stepContent__Ab0mE\"]/div/*[position()=1]")
	private WebElement tituloLabel;

	@FindBy(xpath = "//*[@class=\"image_stepContent__Ab0mE\"]/div/input[2]")
	private WebElement campoURLFile;

	@FindBy(xpath = "//*[@class=\"image_stepContent__Ab0mE\"]/div/input[1]")
	private WebElement campoFile;

	@FindBy(xpath = "//*[@class=\"image_buttons__bx36y\"]/button[contains(text(), \"PRÓXIMO >>\")]")
	private WebElement botaoProximo;
	
	@FindBy(xpath = "//*[@class=\"image_buttons__bx36y\"]/button[contains(text(), \"<< VOLTAR\")]")
	private WebElement botaoVoltar;
	
	@FindBy(xpath = "//*[@class=\"image_buttons__bx36y\"]/button[contains(text(), \"REINICIAR\")]")
	private WebElement botaoReiniciar;

	@FindBy(xpath = "//*[@class=\"image_stepdownload__-Rj2f\"]/div/button[contains(text(), \"Baixar\")]")
	private WebElement botaoBaixar;

	@FindBy(xpath = "//*[@class=\"image_stepContent__Ab0mE\"]/div/div/button")
	private List<WebElement> pipelines;
	
	
	public void selecionarPipeline(String nome) {
		for(WebElement e : pipelines) {
			if(e.getText().toLowerCase().equals(nome.toLowerCase())) {
				e.click();
				break;
			}
		}
	}
	
	public String getTituloLabel() {
		return tituloLabel.getText();
	}

	public void inserirFile(String endereco) {
		campoFile.sendKeys(endereco);
	}
	
	public void inserirURLFile(String URL) {
		campoURLFile.clear();
		campoURLFile.sendKeys(URL);
	}
	
	public void clicarBotaoProximo() {
		botaoProximo.click();
	}
	
	public void clicarBotaoReiniciar() {
		botaoReiniciar.click();
	}
	
	public void clicarBotaoVoltar() {
		botaoVoltar.click();
	}
	
	public void clicarBotaoBaixar() {
		botaoBaixar.click();
	}
}
