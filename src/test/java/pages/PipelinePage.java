package pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static config.ConfigInit.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PipelinePage {

	@FindBy(xpath = "//*[@class=\"styles_labelMain__z9DFn\"]/label")
	private WebElement textoPadrao;
	
	@FindBy(xpath = "//input[@id=\"pipeline-name\"]")
	private WebElement campoNomePipeline;
	
	@FindBy(xpath = "//*[@class=\"modal-body\"]/form/input")
	private WebElement campoNomeCriarPipeline;

	@FindBy(xpath = "//*[@class=\"modal-body\"]/form/div[2]/button")
	private WebElement botaoCriarPipeline;
	
	@FindBy(xpath = "//*[@class=\"modal-header\"]/div/button")
	private WebElement botaoFecharCardCriarPipeline;
	
	@FindBy(xpath = "//nav/*/*/*/span[@role=\"button\"]")
	private WebElement botaoNovaPipeline;

	@FindBy(xpath = "//nav/div[1]/h6")
	private WebElement botaoVoltarPipeline;
	
	@FindBy(xpath = "//*[@class=\"mx-4 mt-4 mb-1 styles_listPipeline__4lUxZ list-group\"]/div[@class=\"styles_list__wTMpi list-group-item list-group-item-light\"]")
	private List<WebElement> pipelines;
	
	@FindBy(xpath = "//*[@class=\"pipeline-save d-flex justify-content-end\"]/div/div/input")
	private WebElement AtivarDesativarPipelineInterno; 
	
	@FindBy(xpath = "//*[@class=\"pipeline-save d-flex justify-content-end\"]/a[@class=\"btn btn-light btn-sm btn-excluir button-default\"]")
	private WebElement linkExcluirPipeline;
	
	@FindBy(xpath = "//*[@class=\"pipeline-save d-flex justify-content-end\"]/a[@class=\"align-self-center px-2 history\"]")
	private WebElement linkHistorico; 
	
	@FindBy(xpath = "//*[@class=\"pipeline-save d-flex justify-content-end\"]/button")
	private WebElement botaoSalvarPipeline;

	@FindBy(xpath = "//*[@class=\"row row-body\"]/div[1]/div[@class=\"input-group a\"]/select")
	private WebElement selectCameras;
	
	@FindBy(xpath = "//*[@class=\"row row-body\"]/div[2]/div/div[@class=\"card-header pipeline-header2 \"]/menu/div/button")
	private WebElement botaoFluxo;
	
	@FindBy(xpath = "//*[@class=\"modal-dialog\"]/div")
	private WebElement modalExcluirPipeline;

	@FindBy(xpath = "//*[@class=\"accordeon-pdi accordion accordion-flush\"]/div[1]/h2/button")
	private WebElement acordServicos;
	
	@FindBy(xpath = "//*[@class=\"accordeon-pdi accordion accordion-flush\"]/div[1]/div/div/ul/li[@role=\"button\"]")
	private List<WebElement> listaDeServicos;
	
	@FindBy(xpath = "//*[@class=\"accordeon-pdi accordion accordion-flush\"]/div[2]/h2/button")
	private WebElement acordPipelines;
	
	@FindBy(xpath = "//*[@class=\"accordeon-pdi accordion accordion-flush\"]/div[2]/div/div/ul/li[@role=\"button\"]")
	private List<WebElement> listaDePipelines;
	
	@FindBy(xpath = "//*[@class=\"row row-body\"]/div[2]/div/div[@class=\"card-body pipeline-card\"]/div/div/div")
	private List<WebElement> ListaDeProcessos;
	
	@FindBy(xpath = "//*[@class=\"row row-body\"]/div[3]/div/div[@class=\"card-body pipeline-card-parameter\"]/div[@class=\"mb-3\" or @class=\"form-check\"]")
	private List<WebElement> ListaDeParametros;
	
	public boolean isPipelinesEmpty() {
		return pipelines.size() > 0? false: true;
	}
	
	public boolean isPageSelectPipeline() {
		return textoPadrao.isDisplayed();
	}
	
	public String getCampoNomePipeline() {
		return campoNomePipeline.getAttribute("value");
	}
	
	public void clickBotaoNovaPipeline() {
		botaoNovaPipeline.click();
	}
	
	public void clickBotaoVoltarPipelines() {
		botaoVoltarPipeline.click();;
	}
	
	public void clickAcordServicos() {
		acordServicos.click();
	}
	
	public void clickAcordPipelines() {
		acordPipelines.click();
	}
	
	public void clickCriarPipeline() {
		botaoCriarPipeline.click();
	}
	
	public void clickBotaoPagePipeline(String value) {
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	        	switch (value) {
				case "Salvar":
					clickBotaoSalvarPipeline();
					break;
					
				case "Excluir":
					clickBotaoExcluirPipeline();
					break;
					
				case "Historico":
					clickBotaoHistorioPipeline();
					break;
					
				default:
					break;
				}
	        	
	        	break;
	        } catch(org.openqa.selenium.ElementClickInterceptedException err) {
	        	esperar(1);
	        }
	        attempts++;
	    }
	}
	
	private void clickBotaoExcluirPipeline() {
		linkExcluirPipeline.click();
		setModalExclirServico("Excluir");
	}
	
	private void clickBotaoHistorioPipeline() {
		linkHistorico.click();
	}
	
	private void clickBotaoSalvarPipeline() {
		botaoSalvarPipeline.click();
	}
	
	public void clickBotaoFluxoPipeline() {
		botaoFluxo.click();
	}
	
	public void inserirCampoNomePipeline(String nomePipeline) {
		campoNomePipeline.clear();
		campoNomePipeline.sendKeys(nomePipeline);;
	}
	
	public void inserirCampoNomeCriarPipeline(String nomePipeline) {
		campoNomeCriarPipeline.clear();
		campoNomeCriarPipeline.sendKeys(nomePipeline);;
	}
	
	public void ativarDesativarPipelineCheckboxInterno(boolean check) {
		boolean checkbox = AtivarDesativarPipelineInterno.isSelected();
		if(checkbox !=check) {
			AtivarDesativarPipelineInterno.click();
		}
	}
	
	public List<String> getPipelinesCriadas(){
		List<String> nomePipelines = new ArrayList<String>();
		for(WebElement e : pipelines) {
			WebElement em = e.findElement(By.xpath("div[1]"));
			nomePipelines.add(em.getText());
		}
		return nomePipelines;
	}
	
	public boolean verificarExistenciaPipeline(String nome) {
		for(String n : getPipelinesCriadas()) {
			if(n.toLowerCase().trim().equals(nome.toLowerCase().trim())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean verificarIsPipelineAtivaExterno(String nome) { 
		esperar(1);
		WebElement pipelineReturn = null;
		for(WebElement e : pipelines) {
			WebElement em = e.findElement(By.xpath("div[1]"));
			if(em.getText().toLowerCase().trim().equals(nome.toLowerCase().trim())) {
				pipelineReturn = e;
			}
		}
		if(pipelineReturn != null) {
			WebElement checkBox = pipelineReturn.findElement(By.xpath("div[2]/form/div/input"));
			return checkBox.isSelected();
		}else {
			System.err.println("Pipeline " + nome + " Não foi encontrada");
		}
		return false;
	}
	
	public void selectPipeline(String nome, String acao) {
		esperar(1);
		WebElement pipelineReturn = null;
		for(WebElement e : pipelines) {
			WebElement em = e.findElement(By.xpath("div[1]"));
			if(em.getText().toLowerCase().trim().equals(nome.toLowerCase().trim())) {
				pipelineReturn = e;
			}
		}
		if(pipelineReturn != null) {
			WebElement checkBox = pipelineReturn.findElement(By.xpath("div[2]/form/div/input"));
			WebElement botaoEditarPipeline = pipelineReturn.findElement(By.xpath("div[2]/button[1]"));
			WebElement botaoDeletarPipeline = pipelineReturn.findElement(By.xpath("div[2]/button[2]"));
			boolean ischeck = checkBox.isSelected();
			
			
			switch (acao) {
			case "Ativar":
				
				if(ischeck != true) {
					checkBox.click();
				}
				break;
			case "Desativar":
				
				if(ischeck != false) {
					checkBox.click();
				}
				break;
				
			case "Editar":
				
				pipelineReturn.click();
				break;
				
			case "Deletar":
				
				botaoDeletarPipeline.click();
				setModalExclirServico("Excluir");
				break;

			default:
				break;
			}
			
		}else {
			System.err.println("Pipeline " + nome + " Não foi encontrada");
		}
		
	}
	
	public void selecionarCamera(String nomeCamera) {
		Select select = new Select(selectCameras);
		if(opcaoExistenteNoSelect(select, nomeCamera)) {
			select.selectByVisibleText(nomeCamera);
		}else {
			System.err.println("Camera " + nomeCamera + " Não foi encontrado no Select");
		}
	}
	
	public void adicionarPDIEmPipeline(String Nome) {
		boolean valid = false;
		
		String className = acordServicos.getAttribute("class");
		if(className.equals("accordion-button collapsed")) {
			acordServicos.click();
		}
		
		if(!listaDeServicos.isEmpty()) {
			for(WebElement e : listaDeServicos) {
				if(e.getAttribute("innerText").toLowerCase().trim().equals(Nome.toLowerCase().trim())) {
					e.click();
					valid = true;
					break;
				}
			}
			if(!valid) {
				System.err.println("Servico "+ Nome + ", nao encontrado");
			}
		}else {
			System.err.println("Nenhum Servico Cadastrado");
		}
		
	}
	
	public void adicionarPipelineEmPipeline(String Nome) {
		boolean valid = false;
		
		
		String className = acordPipelines.getAttribute("class");
		if(className.equals("accordion-button collapsed")) {
			acordPipelines.click();
		}
		
		if(!listaDePipelines.isEmpty()) {
			for(WebElement e : listaDePipelines) {
				if(e.getAttribute("innerText").toLowerCase().trim().equals(Nome.toLowerCase().trim())) {
					e.click();
					valid = true;
					break;
				}
			}
			if(!valid) {
				System.err.println("Pipeline "+ Nome + ", nao encontrada");
			}
		}else {
			System.err.println("Nenhuma Pipeline disponivel");
		}
		
	}
	
	public int getProcessosPipelineSize() {
		return ListaDeProcessos.size();
	}
	
	public void selecionarProcesso(int id) {
		Optional processo = getProcessoAplicadoAPipeline(id);
		if(processo.isPresent()) {
			WebElement processoRecuperado = (WebElement) processo.get();
			processoRecuperado.click();
		}else {
			System.err.println("O processo ID: " + id + " Nao esta sendo aplicado à Pipeline" );
		}
	}
	
	public List<String> getNomeProcessosPipeline(){
		List<String> processos = new ArrayList<String>();
		if(!ListaDeProcessos.isEmpty()) {
			for(WebElement e : ListaDeProcessos) {
				String nome = e.findElement(By.xpath("/div[1]")).getText();
				processos.add(nome);
			}
		}else {
			System.err.println("Nenhum Servico ou Pipeline foi adicionado a esta Pipeline");
		}
		return processos;
	}
	
	public boolean isProcessoEstaSendoAplicado(String nome) {
		for(String processo : getNomeProcessosPipeline()) { 
			if(processo.toLowerCase().equals(nome.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
	public boolean isProcessoEstaSendoAplicado(int id) {
		Optional processo = getProcessoAplicadoAPipeline(id);
		if(processo.isPresent()) { 
			return true;
		}
		return false;
	}
	
	public boolean isPipelineAtiva() {
		esperar(1);
		boolean checkbox = AtivarDesativarPipelineInterno.isSelected();
		System.err.println(checkbox);;
		if(checkbox) {
			return true;
		}
		return false;
	}
	
	public void removerProcessoDaPipeline(String nome) {
		Optional processo = getProcessoAplicadoAPipeline(nome);
		if(processo.isPresent()) {
			WebElement processoRecuperado = (WebElement) processo.get();
			WebElement botaoRemover = processoRecuperado.findElement(By.xpath("div[3]/i"));
			botaoRemover.click();
		}else {
			System.err.println("O processo " + nome + " Nao esta sendo aplicado à Pipeline" );
		}
	}
	
	public void removerProcessoDaPipeline(int id) {
		Optional<WebElement> processo = getProcessoAplicadoAPipeline(id);
		if(processo.isPresent()) {
			WebElement processoRecuperado = processo.get();
			WebElement botaoRemover = processoRecuperado.findElement(By.xpath("div[3]/i"));
			botaoRemover.click();
		}else {
			System.err.println("O processo ID: " + id + " Nao esta sendo aplicado à Pipeline" );
		}
	}
	
	public Optional<WebElement> getParametroInputServico(String value){
		for(WebElement e : ListaDeParametros) {
			if(e.findElement(By.xpath("label")).getText().toLowerCase().equals(value.toLowerCase())) {
				return Optional.of(e.findElement(By.xpath("input")));
			}
		}
		System.err.println("O Parametro: " + value + " Nao foi encontrado" );
		return Optional.empty();
	}
	
	public Optional<WebElement> getParametroSelectServico(String value){
		for(WebElement e : ListaDeParametros) {
			if(e.findElement(By.xpath("label")).getText().toLowerCase().replace("*", "").equals(value.toLowerCase())) {
				return Optional.of(e.findElement(By.xpath("select")));
			}
		}
		return Optional.empty();
	}
	
	public void inserirParametroTipoString(String nomeParam, String value) {
		Optional<WebElement> parametroRecuperado = getParametroInputServico(nomeParam);
		
		if(parametroRecuperado.isPresent()) {
			WebElement parametro = parametroRecuperado.get();
			parametro.clear();
			parametro.sendKeys(value);
		}else {
			System.err.println("O parametro: " + nomeParam + " Nao foi encontrado" );
		}
		
	}
	
	public void inserirParametroTipoBoolean(String nomeParam, boolean value) {
		Optional<WebElement> parametroRecuperado = getParametroInputServico(nomeParam);
		
		if(parametroRecuperado.isPresent()) {
			WebElement parametro = parametroRecuperado.get();
			WebElement parametroCheck = parametro.findElement(By.xpath("/input"));
			boolean checkbox = parametroCheck.isSelected();
			if(checkbox != value) {
				parametroCheck.click();
			}
		}else {
			System.err.println("O parametro: " + nomeParam + " Nao foi encontrado" );
		}
		
	}
	
	public void inserirParametroTipoSelect(String nomeParam, String value) {
		Optional<WebElement> parametroRecuperado = getParametroSelectServico(nomeParam);
		
		if(parametroRecuperado.isPresent()) {
			Select parametro = new Select(parametroRecuperado.get());
			parametro.selectByValue(value);
		}else {
			System.err.println("O parametro: " + nomeParam + " Nao foi encontrado" );
		}
		
	}
	
	public boolean verificarParametroTipoString(String nomeParam, String value) {
		Optional<WebElement> parametroRecuperado = getParametroInputServico(nomeParam);
	
		if(parametroRecuperado.isPresent()) {
			WebElement parametro = parametroRecuperado.get();
			String valor = parametro.getAttribute("value");
			System.err.println(parametro.getAttribute("value") + " == " + value);
			if(valor.equals(value)) {
				return true;
			}
		}
		System.err.println("Parametro não encontrado");
		return false;
	
	}
	
	private boolean opcaoExistenteNoSelect(Select select, String value) {
		for(WebElement e : select.getOptions()) {
			if(e.getText().toLowerCase().equals(value.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	private Optional<WebElement> getProcessoAplicadoAPipeline(String nome) {
		if(isProcessoEstaSendoAplicado(nome)) {
			for(WebElement e : ListaDeProcessos){ 
				WebElement Nome = e.findElement(By.xpath("/div[1]"));
				if(Nome.getText().toLowerCase().equals(nome.toLowerCase())) {
					return Optional.of(e);
				}
			}
		}
		System.err.println("O processo " + nome + " Nao esta sendo aplicado à Pipeline" );
		return Optional.empty();
	}
	
	private Optional<WebElement> getProcessoAplicadoAPipeline(int id) {
		for(WebElement e : ListaDeProcessos){ 
			if(Integer.parseInt(e.getAttribute("id")) == id) {
				return Optional.of(e);
			}
		}
		System.err.println("O processo ID: " + id + " Nao esta sendo aplicado à Pipeline" );
		return Optional.empty();
	}
	
	public void setModalExclirServico(String value) {
		switch (value) {
		
		case "Fechar":
			modalExcluirPipeline.findElement(By.xpath("div[1]/button")).click();;
			break;
			
		case "Cancelar":
			modalExcluirPipeline.findElement(By.xpath("div[3]/button[text() = \"Cancelar\"]")).click();;
			break;
			
		case "Excluir":
			modalExcluirPipeline.findElement(By.xpath("div[3]/button[text() = \"Excluir\"]")).click();;
			break;

		default:
			break;
		}
	}
}
