package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HistoricoPipelinePage {

	@FindBy(xpath = "//nav/div/a")
	private WebElement titulo; 
	
	@FindBy(xpath = "//nav/div/div/a")
	private WebElement botaoVoltar;
	
	@FindBy(xpath = "//*[@class=\"list-group-item list-group-item-light\"]")
	private List<WebElement> versoes; 
	
	public String getTituloPage() {
		return titulo.getText();
	}
	
	public void clickBotaoVoltar() {
		botaoVoltar.click();
	}
	
	public List<String> getVersoesName() { 
		List<String> tituloVersoes = new ArrayList<String>();
		for(WebElement e : versoes) {
			tituloVersoes.add(e.findElement(By.xpath("input")).getAttribute("placeholder"));
		}
		return tituloVersoes;
	}
	
	public void restaurarVersao(int index) {
		if(index<0) {
			versoes.get(versoes.size()-1).findElement(By.xpath("div/button")).click();;
		}
		versoes.get(index-1).findElement(By.xpath("div/button")).click();
	}
	
	public int getQtddDeVersoes() {
		return versoes.size();
	}
}
