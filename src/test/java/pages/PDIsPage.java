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
	
	public int getTotalElements() {
		return parametros.size();
	}
	
	public void inserirCampoNomeParametro(int posicao, String value) {
		WebElement temp = parametros.get(posicao-1);
		WebElement imput = temp.findElement(By.xpath("div/input"));
		imput.clear();
		imput.sendKeys(value);
//		WebElement imput = temp.findElement(By.xpath("//*div[@class=\"card\" and position()="+posicao+"]/div/input[@id=\""+ (posicao-1) +"\"]"));
	}
	
	public void selecionarTipoParametro(int posicao,int tipo) {
		WebElement temp = parametros.get(posicao-1);
		Select select = new Select( temp.findElement(By.xpath("div/select")));
		select.selectByVisibleText(tipo == 0? "STRING" : tipo == 1 ? "NUMBER": null );
		
	}
	
	public void ClicarObrigatoriedadeParametro(int posicao) {
		WebElement temp = parametros.get(posicao-1);
		WebElement imput = temp.findElement(By.xpath("div/div/input"));
		imput.click();
		
	}
	
	public void ClicarDeletarParametro(int posicao) {
		WebElement temp = parametros.get(posicao-1);
		WebElement imput = temp.findElement(By.xpath("div/i"));
		imput.click();
		
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
