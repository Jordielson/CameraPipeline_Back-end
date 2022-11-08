package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FluxoPage {

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/button")
	private WebElement botaoSalvarEVoltar;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div[2]/div[2]/a")
	private WebElement botaoVoltar;
	
	@FindBy(xpath = "//*[@aria-describedby=\"react-flow__node-desc-1\"]")
	private List<WebElement> processos;
	
	public int getQtddProcessos() {
		return processos.size();
	}
	
	public void clickBotaoSalvarEVoltar() {
		botaoSalvarEVoltar.click();
	}
	
	public void clickBotaoVoltar() {
		botaoVoltar.click();
	}
	
	public List<WebElement> getAllProcessos() {
		return processos;
	}
	
	public WebElement getProcessoPorID(int id) {
		WebElement retorno = null;
		
		for(WebElement e : processos) {
			if((Integer.parseInt(e.getAttribute("data-id"))) == id) {
				retorno = e;
			}
		}
		
		if(retorno==null) {
			System.err.println("processo ID: " + id +" Não foi encontrado");
		}
		return retorno;
		
	}
	
	public WebElement getAncoraProcesso(int id, String ancora) {
		WebElement retorno = getProcessoPorID(id);
		WebElement ancoraReturn = null;
		
		if(ancora.toLowerCase().trim().equals("top")) {
			ancoraReturn =retorno.findElement(By.xpath("div[@data-handlepos=\"top\"]"));
		}else {
			ancoraReturn =retorno.findElement(By.xpath("div[@data-handlepos=\"bottom\"]"));
		}
		System.err.println(retorno.getAttribute("data-id"));
		
		return ancoraReturn;
		
	}
	
}
