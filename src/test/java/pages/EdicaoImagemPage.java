package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EdicaoImagemPage {

	@FindBy(xpath = "//*[@class=\"image_stepContent__bGGiG\"]/div/input[2]")
	private WebElement campoURLFile;

	@FindBy(xpath = "//*[@class=\"image_stepContent__bGGiG\"]/div/input[1]")
	private WebElement campoFile;

	@FindBy(xpath = "//*[@class=\"image_buttons__Ut1An\"]/button[contains(text(), \"PRÓXIMO >>\")]")
	private WebElement botaoProximo;
	
	@FindBy(xpath = "//*[@class=\"image_buttons__Ut1An\"]/button[contains(text(), \"<< VOLTAR\")]")
	private WebElement botaoVoltar;
	
	@FindBy(xpath = "//*[@class=\"image_buttons__Ut1An\"]/button[contains(text(), \"REINICIAR\")]")
	private WebElement botaoReiniciar;

	@FindBy(xpath = "//*[@class=\"image_stepdownload__QWaVR\"]/div/button[contains(text(), \"Baixar\")]")
	private WebElement botaoBaixar;

	@FindBy(xpath = "//*[@class=\"image_stepContent__bGGiG\"]/div/div/button")
	private List<WebElement> pipelines;
	
	
	public void selecionarPipeline(String nome) {
		for(WebElement e : pipelines) {
			if(e.getText().toLowerCase().equals(nome.toLowerCase())) {
				e.click();
				break;
			}
		}
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
