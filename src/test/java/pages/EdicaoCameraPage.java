package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EdicaoCameraPage {

	@FindBy(xpath = "//*[@class=\"image_stepContent__bGGiG\"]/div/*[position()=1]")
	private WebElement tituloLabel;

	@FindBy(xpath = "//*[@class=\"image_buttons__Ut1An\"]/button[contains(text(), \"PRÓXIMO >>\")]")
	private WebElement botaoProximo;
	
	@FindBy(xpath = "//*[@class=\"image_buttons__Ut1An\"]/button[contains(text(), \"<< VOLTAR\")]")
	private WebElement botaoVoltar;
	
	@FindBy(xpath = "//*[@class=\"image_buttons__Ut1An\"]/button[contains(text(), \"REINICIAR\")]")
	private WebElement botaoReiniciar;

	@FindBy(xpath = "//*[@class=\"image_stepdownload__QWaVR\"]/div/div/button[contains(text(), \"Salvar\")]")
	private WebElement botaoSalvar;
	
	@FindBy(xpath = "//*[@class=\"image_stepContent__bGGiG\"]/div/div/button")
	private List<WebElement> pipelines;
	
	@FindBy(xpath = "//*[@class=\"mx-4 mt-4 mb-1 styles_listCamera__htA92 list-group\"]/div[@class=\"styles_item__L4S8m list-group-item list-group-item-light\"]")
	private List<WebElement> cameras;
	
	public String selecionarCamera(String nome) {
		for(WebElement e : cameras) {
			
			
			if(e.getText().equals(nome)) {
				WebElement radio = e.findElement(By.xpath("div/input"));
				System.out.println(radio.isSelected());
				if(!radio.isSelected()) {
					radio.click();
					return e.getText();
				}
			}
		}
		System.out.println("Saiu errado");
		return "Nenhuma Camera Selecionada";
	}
	
	public String recuperarNomeCameraSelecionada() {	
		for(WebElement e : cameras) {
			System.out.println(e.getText());
			WebElement radio = e.findElement(By.xpath("div/input"));
			if(radio.isSelected()) {
				return e.getText();
			}
		}
		return "Nenhuma Camera Selecionada";
	}
	
	public void visualizarCamera(String nome) {
		for(WebElement e : cameras) {
			System.out.println(e.getText());
			if(e.getText().equals(nome)) {
				WebElement button = e.findElement(By.xpath("div/button"));
				button.click();
			}
		}
	}
	
	public String selecionarPipeline(String nome) {
		for(WebElement e : pipelines) {
			if(e.getText().toLowerCase().equals(nome.toLowerCase())) {
				String retorno = e.getText();
				e.click();
				return retorno;
			}
		}
		System.out.println("Saiu errado");
		return "Pipeline não encontrada";
	}
	
	public String getTituloLabel() {
		return tituloLabel.getText();
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
	
	public void clicarBotaoSalvar() {
		botaoSalvar.click();
	}
}
