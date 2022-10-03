package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PDIsPage {
	
	@FindBy(xpath = "/html/body/div/div/div[1]/div[2]/nav/div/div/div[2]/button")
	private WebElement botaoAdicionarNovoPDI;
	
	@FindBy(xpath = "//*[@class=\"navbar navbar-dark navpdi\"]/div/div/div[1]/input")
	private WebElement campoPesquisaPDI;
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[1]/div/div[1]/div/input")
	private WebElement campoNomePDI;
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[1]/div/div[2]/input")
	private WebElement campoURL;
	
	@FindBy(xpath = "/html/body/div[3]/div/div/div[3]/div[2]/button")
	private WebElement botaoSalvar;
	
	@FindBy(xpath = "/html/body/div[3]/div/div/div[3]/div[1]/button")
	private WebElement botaoNovoParametro;
	
	@FindBy(xpath = "//*[@class=\"Pdi_modal__2LEg0 modal-body\"]/div[@class=\"card\"]")
	private List<WebElement> parametros; 
	
	@FindBy(xpath = "//*[@class=\"mx-4 mt-4 mb-1 listpdi list-group\"]/div[@class=\"list-item list-group-item list-group-item-light\"]")
	private List<WebElement> PDIs;
	
	public int getTotalElements() {
		return parametros.size();
	}
	
	public void deletarTodosPDIs() {
		for(WebElement e : PDIs) {
			deletarPDIPorWebElement(e);
		}
	}
	
	private void deletarPDIPorWebElement(WebElement element) {
		WebElement botao = element.findElement(By.xpath("//*[@class=\"buttons\"]/button[@title=\"EXCLUIR\"]"));
		botao.click();
	}
	
	public void inserirCampoNomeParametro(int posicao, String value) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div/input"));
		input.clear();
		input.sendKeys(value);
//		WebElement imput = temp.findElement(By.xpath("//*div[@class=\"card\" and position()="+posicao+"]/div/input[@id=\""+ (posicao-1) +"\"]"));
	}
	
	public String getNomeParametro(int posicao) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div/input"));
		return input.getAttribute("value");
	}
	
	public void selecionarTipoParametro(int posicao,int tipo) {
		WebElement temp = parametros.get(posicao-1);
		Select select = new Select( temp.findElement(By.xpath("div/select")));
		select.selectByVisibleText(tipo == 0? "STRING" : tipo == 1 ? "NUMBER": null );
		
	}
	
	public int getTipoParametro(int posicao) {
		WebElement temp = parametros.get(posicao-1);
		Select select = new Select( temp.findElement(By.xpath("div/select")));
		String opcao = select.getFirstSelectedOption().getAttribute("innerText");
		return opcao.equals("STRING")? 0: opcao.equals("NUMBER")? 1 : null;
	}
	
	
	public void setObrigatoriedadeDoParametro(int posicao, boolean isObrigatorio) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div/div/input"));
		boolean checkbox = input.isSelected(); 
		if(checkbox != isObrigatorio) {
			input.click();
		}
	}

	public boolean getObrigatoriedadeDoParametro(int posicao) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div/div/input"));
		return input.isSelected(); 
	}
	
	public void ClicarDeletarParametro(int posicao) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div/i"));
		input.click();
		
	}
	
	public void clicarBotaoAdicionarNovoPDI() {
		botaoAdicionarNovoPDI.click();
	}
	
	public void clicarBotaoSalvar() {
		botaoSalvar.click();
	}
	
	public void clicarBotaoNovoParametro() {
		botaoNovoParametro.click();
	}
	
	public void inserirCampoPesquisaPDI(String value) {
		campoPesquisaPDI.clear();
		campoPesquisaPDI.sendKeys(value);
	}
	public void inserirCampoNomePDI(String value) {
		campoNomePDI.clear();
		campoNomePDI.sendKeys(value);
	}
	public void inserirCampoURL(String value) {
		campoURL.clear();
		campoURL.sendKeys(value);
	}

}