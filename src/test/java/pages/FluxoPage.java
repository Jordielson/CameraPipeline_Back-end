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
	
	@FindBy(xpath = "//*[@class=\"react-flow__edges react-flow__container\"]/*/*[@aria-describedby=\"react-flow__edge-desc-1\"]")
	private List<WebElement> conectores;
	
	@FindBy(xpath = "//*[@class=\"react-flow__panel react-flow__controls bottom left\"]/button[@title=\"zoom in\"]")
	private WebElement botaoZoomIn;
	
	@FindBy(xpath = "//*[@class=\"react-flow__panel react-flow__controls bottom left\"]/button[@title=\"zoom out\"]")
	private WebElement botaoZoomOut;
	
	@FindBy(xpath = "//*[@class=\"react-flow__panel react-flow__controls bottom left\"]/button[@title=\"fit view\"]")
	private WebElement botaoFitView;
	
	@FindBy(xpath = "//*[@class=\"react-flow__panel react-flow__controls bottom left\"]/button[@title=\"toggle interactivity\"]")
	private WebElement botaoToggleInteractivity;
	
	
	public void clickBotaoZoomIn() {
		botaoZoomIn.click();
	}
	
	public void clickBotaoZoomOut() {
		botaoZoomOut.click();
	}
	
	public void clickBotaoFitView() {
		botaoFitView.click();
	}
	
	public void clickBotaoToggleInteractivity() {
		botaoToggleInteractivity.click();
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
	
	public int getConectoresSize() {
		return conectores.size();
	}
	
	public int getProcessosSize() {
		return processos.size();
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
		
		return ancoraReturn;
		
	}
	
	public void clickDeletarConexao(int id1, int id2) {
		
		String ariaLabelValidation = "Edge from "+ id1 +" to "+ id2;
		
		
		for(WebElement e : conectores) {
			String ariaLabel = e.getAttribute("aria-label");
			if(ariaLabel.toLowerCase().trim().equals(ariaLabelValidation.toLowerCase().trim())) {
				WebElement button = e.findElement(By.xpath("*/*/button"));
				button.click();
				return;
			}
		}
		
		System.err.println("Conexão Inexistente");
		
	}
	
	public boolean verificarConexao(int id1, int id2) {
		String ariaLabelValidation = "Edge from "+ id1 +" to "+ id2;
		for(WebElement e : conectores) {
			String ariaLabel = e.getAttribute("aria-label");
			if(ariaLabel.toLowerCase().trim().equals(ariaLabelValidation.toLowerCase().trim())) {
				return true;
			}
		}
		return false;
	}
	
}
