package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class hitoricoPipelinePage {

	@FindBy(xpath = "/nav/div/a")
	private WebElement tituloDaPagina;
	
	@FindBy(xpath = "/nav/div//div/a")
	private WebElement botaoVoltar;
	
	@FindBy(xpath = "/html/body/div/div/div[1]/div[2]/div/div")
	private List<WebElement> listaVersoes;
	
	public String getTituloDaPagina() {
		return tituloDaPagina.getText();
	}
	
	public void clickBotaoVoltar() {
		botaoVoltar.click();
	}
	
	public List<String> getVersoes() {
		List<String> versoes = new ArrayList<String>();
		for(WebElement e : listaVersoes) {
			versoes.add(e.getText());
		}
		return versoes;
	}
	
	public void restaurarVersao(String value) {
		for(WebElement e : listaVersoes) {
			if(e.getText().equals(value)) { 
				WebElement botao = e.findElement(By.xpath("/div/button"));
				botao.click();
				break;
			}
		}
	}
	
	public void restaurarVersao(int index) {
		WebElement botao = listaVersoes.get(index).findElement(By.xpath("/div/button"));
		botao.click();
	}
	
}
