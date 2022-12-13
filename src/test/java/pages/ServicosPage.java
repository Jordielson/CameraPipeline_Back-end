package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ServicosPage {
	
	@FindBy(xpath = "/html/body/div/div/div[1]/div[2]/nav/div/div/div[2]/button")
	private WebElement botaoAdicionarNovoServico;
	
	@FindBy(xpath = "//*[@class=\"navbar navbar-dark navpdi\"]/div/div/div[1]/input")
	private WebElement campoPesquisaServico;
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[1]/div/div[1]/div/input")
	private WebElement campoNomeServico;
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[1]/div/div[2]/input")
	private WebElement campoURL;
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[1]/button")
	private WebElement botaoFecharCardParametro;

	@FindBy(xpath = "//*[@class=\"Pdi_modal__2LEg0 modal-body\"]/div/textarea")
	private WebElement campoDescricaoServico;

	@FindBy(xpath = "/html/body/div[3]/div/div/div[3]/div[2]/button")
	private WebElement botaoSalvar;
	
	@FindBy(xpath = "/html/body/div[3]/div/div/div[3]/div[1]/button")
	private WebElement botaoNovoParametro;
	
	@FindBy(xpath = "//*[@id=\"react-confirm-alert\"]/*/*/div")
	private WebElement modalItemEmUso;
	
	@FindBy(xpath = "//*[@class=\"modal-dialog\"]/div")
	private WebElement modalExcluirServico;
	
	@FindBy(xpath = "//*[@class=\"Pdi_modal__2LEg0 modal-body\"]/div[@class=\"card Pdi_margin__GSzNe\"]")
	private List<WebElement> parametros; 
	
	@FindBy(xpath = "//*[@class=\"mx-4 mt-4 mb-1 listpdi list-group\"]/div[@class=\"list-item list-group-item list-group-item-light\"]")
	private List<WebElement> servicos;
	
	public int getTotalParametros() {
		return parametros.size();
	}
	
	public int getTotalServicos() {
		return servicos.size();
	}

	
	public void deletarTodosServicos() {
		for(WebElement e : servicos) {
			deletarServicoPorWebElement(e);
		}
	}
	
	public void clicarBotaoFecharCardServico() {
		botaoFecharCardParametro.click();
	}
	
	public void inserirCampoDescricaoServico(String value) {
		campoDescricaoServico.clear();
		campoDescricaoServico.sendKeys(value);
	}
	
	public String getCampoDescricaoServico(String value) {
		return campoDescricaoServico.getAttribute("innerText");
	}
	
	private void deletarServicoPorWebElement(WebElement element) {
		WebElement botao = element.findElement(By.xpath("//*[@class=\"buttons\"]/button[@title=\"EXCLUIR\"]"));
		botao.click();
		setModalExclirServico("Excluir");
	}
	
	public void deletarServicoPorNome(String value) {
		WebElement botao = getServicoPorNome(value).findElement(By.xpath("//*[@class=\"buttons\"]/button[@title=\"EXCLUIR\"]"));
		botao.click();
		setModalExclirServico("Excluir");
	}
	
	public void setModalExclirServico(String value) {
		switch (value) {
		
		case "fechar":
			modalExcluirServico.findElement(By.xpath("div[1]/button")).click();;
			break;
			
		case "Cancelar":
			modalExcluirServico.findElement(By.xpath("div[3]/button[text() = \"Cancelar\"]")).click();;
			break;
			
		case "Excluir":
			modalExcluirServico.findElement(By.xpath("div[3]/button[text() = \"Excluir\"]")).click();;
			break;

		default:
			break;
		}
	}
	
	public void clickBotaoEditarServico(String value) {
		getServicoPorNome(value).findElement(By.xpath("div/button[@title=\"EDITAR\"]")).click();
	}
	
	public void inserirCampoNomeParametro(int posicao, String value) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div[1]/input"));
		input.clear();
		input.sendKeys(value);
//		WebElement imput = temp.findElement(By.xpath("//*div[@class=\"card\" and position()="+posicao+"]/div/input[@id=\""+ (posicao-1) +"\"]"));
	}
	
	public String getNomeParametro(int posicao) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div[1]/input"));
		return input.getAttribute("value");
	}
	
	public void selecionarTipoParametro(int posicao,int tipo) {
		WebElement temp = parametros.get(posicao-1);
		Select select = new Select( temp.findElement(By.xpath("div[1]/select")));
		select.selectByVisibleText(tipo == 0? "STRING" : tipo == 1 ? "NUMBER": tipo == 2 ? "BOOLEAN" : tipo == 3? "FILE" : tipo == 4? "SELECT" : tipo == 5? "COLOR" : null);
		
	}
	
	public int getTipoParametro(int posicao) {
		WebElement temp = parametros.get(posicao-1);
		Select select = new Select( temp.findElement(By.xpath("div[1]/select")));
		String opcao = select.getFirstSelectedOption().getAttribute("innerText");
		return opcao.equals("STRING")? 0: opcao.equals("NUMBER")? 1 : opcao.equals("BOOLEAN")? 2: opcao.equals("FILE")? 3 : opcao.equals("SELECT")? 4 : opcao.equals("COLOR")? 5 : null;
	}
	
	
	public void setObrigatoriedadeDoParametro(int posicao, boolean isObrigatorio) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div[1]/div/input"));
		boolean checkbox = input.isSelected(); 
		if(checkbox != isObrigatorio) {
			input.click();
		}
	}

	public boolean getObrigatoriedadeDoParametro(int posicao) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div[1]/div/input"));
		return input.isSelected(); 
	}
	
	public void ClicarDeletarParametro(int posicao) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div[1]/i"));
		input.click();
		
	}
	
	public void InserirDescricaoParametro(int posicao, String value) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div[2]/textarea"));
		input.clear();
		input.sendKeys(value);
	}
	public String getDescricaoParametro(int posicao) {
		WebElement temp = parametros.get(posicao-1);
		WebElement input = temp.findElement(By.xpath("div[2]/textarea"));
		return input.getText();
	}
	
	
	public void setModalItemEmUso(String value) {
		switch (value) {
		case "Deletar":
			modalItemEmUso.findElement(By.xpath("/button[text() = \"Deletar\"]")).click();;
			break;

		default:
			modalItemEmUso.findElement(By.xpath("/button[text() = \"Cancelar\"]")).click();;
			break;
		}
	}
	
	public WebElement getServicoPorNome(String value) {
		for(WebElement e : servicos) { 
			if(e.getText().trim().toLowerCase().equals(value.trim().toLowerCase())) {
				return e;
			}
		}
		System.err.println("Serviço " + value + " Não encontrado");
		return null;
	}
	
	public void clicarBotaoAdicionarNovoServico() {
		botaoAdicionarNovoServico.click();
	}
	
	public void clicarBotaoSalvar() {
		botaoSalvar.click();
	}
	
	public void clicarBotaoNovoParametro() {
		botaoNovoParametro.click();
	}
	
	public void inserirCampoPesquisaServico(String value) {
		campoPesquisaServico.clear();
		campoPesquisaServico.sendKeys(value);
	}
	public void inserirCampoNomeServico(String value) {
		campoNomeServico.clear();
		campoNomeServico.sendKeys(value);
	}
	public void inserirCampoURL(String value) {
		campoURL.clear();
		campoURL.sendKeys(value);
	}

}
