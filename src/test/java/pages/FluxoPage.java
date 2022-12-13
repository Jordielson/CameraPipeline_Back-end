package pages;

import java.util.List;
import static config.ConfigInit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FluxoPage {

	@FindBy(xpath = "//*[@class=\"undefined p-3\"]/button[@class=\"btn btn-color Styles_btn__k3TRa\"]")
	private WebElement botaoSalvar;
	
	@FindBy(xpath = "//*[@class=\"undefined p-3\"]/button[@class=\"Styles_back__F0CPa btn btn-color\"]")
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
		botaoSalvar.click();
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
	
	public WebElement getProcessoPorID(String id) {
		WebElement retorno = null;
		
		for(WebElement e : processos) {
			if(e.getAttribute("data-id").toLowerCase().trim().equals(id.toLowerCase().trim())) {
				retorno = e;
			}
		}
		
		if(retorno==null) {
			System.err.println("processo ID: " + id +" Não foi encontrado");
		}
		return retorno;
		
	}
	
	public WebElement getAncoraProcesso(String id, String ancora) {
		WebElement retorno = getProcessoPorID(id);
		WebElement ancoraReturn = null;
		
		if(ancora.toLowerCase().trim().equals("top")) {
			ancoraReturn =retorno.findElement(By.xpath("div[@data-handlepos=\"top\"]"));
		}else {
			ancoraReturn =retorno.findElement(By.xpath("div[@data-handlepos=\"bottom\"]"));
		}
		
		return ancoraReturn;
		
	}
	
	public void clickDeletarConexao(String id1, String id2) {
		
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
	public void clickDeletarConexoes() {
		int qtddConexoes = getConectoresSize();
		for(int i = 0; i<qtddConexoes; i++) {
				WebElement e = conectores.get(0);
			    int attempts = 0;
			    while(attempts < 2) {
			        try {
			        	e.findElement(By.xpath("*/*/button")).click();
			            break;
			        } catch(org.openqa.selenium.StaleElementReferenceException err) {
			        }
			        attempts++;
			    }
		}
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
