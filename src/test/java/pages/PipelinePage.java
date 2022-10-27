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

	@FindBy(xpath = "//*[@class=\"empty-pipeline\"]/h2")
	private WebElement textoPadrao;
	
	@FindBy(xpath = "//nav/input")
	private WebElement campoNomePipeline;
	
	@FindBy(xpath = "//nav/*/*/input")
	private WebElement campoNomeCriarPipeline;
	
	@FindBy(xpath = "//nav/*/*/button")
	private WebElement botaoCriarPipeline;
	
	@FindBy(xpath = "//*[@id=\"pipelines-select\"]")
	private WebElement selectPipelines;
	
	@FindBy(xpath = "//*[@class=\"pipeline-save d-flex justify-content-end\"]/div/div/input")
	private WebElement checkboxPipeline; 
	
	@FindBy(xpath = "//*[@class=\"pipeline-save d-flex justify-content-end\"]/a[@class=\"p-delete\"]")
	private WebElement linkExcluirPipeline;
	
	@FindBy(xpath = "//*[@class=\"pipeline-save d-flex justify-content-end\"]/a[2]")
	private WebElement linkHistorico; 
	
	@FindBy(xpath = "//*[@class=\"pipeline-save d-flex justify-content-end\"]/button")
	private WebElement botaoSalvarPipeline;

	@FindBy(xpath = "//*[@class=\"row row-body\"]/div[1]/div[@class=\"input-group a\"]/select")
	private WebElement selectCameras;
	
	@FindBy(xpath = "//*[@class=\"row row-body\"]/div[2]/div/div[@class=\"card-header pipeline-header2\"]/menu/div/button")
	private WebElement botaoFluxo;

	@FindBy(xpath = "//*[@class=\"accordeon-pdi accordion accordion-flush\"]/div[1]/h2/button")
	private WebElement acordPDIs;
	
	@FindBy(xpath = "//*[@class=\"accordeon-pdi accordion accordion-flush\"]/div[1]/div/div/ul/button")
	private List<WebElement> listaDePDIs;
	
	@FindBy(xpath = "//*[@class=\"accordeon-pdi accordion accordion-flush\"]/div[2]/h2/button")
	private WebElement acordPipelines;
	
	@FindBy(xpath = "//*[@class=\"accordeon-pdi accordion accordion-flush\"]/div[2]/div/div/ul/button")
	private List<WebElement> listaDePipelines;
	
	@FindBy(xpath = "//*[@class=\"row row-body\"]/div[2]/div/div[@class=\"card-body pipeline-card\"]/div/div/div")
	private List<WebElement> ListaDeProcessos;
	
	@FindBy(xpath = "//*[@class=\"row row-body\"]/div[3]/div/div[@class=\"card-body pipeline-card\"]/div[@class=\"mb-3\" or @class=\"form-check\"]")
	private List<WebElement> ListaDeParametros;
	
	public boolean isTextoPadraoPresente() {
		return textoPadrao.isDisplayed();
	}
	
	public String getTextoPadrao() {
		return textoPadrao.getText();
	}
	
	public String getCampoNomePipeline() {
		return campoNomePipeline.getAttribute("value");
	}
	
	public void clickAcordPDIs() {
		acordPDIs.click();
	}
	
	public void clickAcordPipelines() {
		acordPipelines.click();
	}
	
	public void clickCriarPipeline() {
		botaoCriarPipeline.click();
	}
	
	public void clickBotaoExcluirPipeline() {
		linkExcluirPipeline.click();
	}
	
	public void clickBotaoHistorioPiprline() {
		linkHistorico.click();
	}
	
	public void clickBotaoSalvarPipeline() {
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
	
	public void ativarDesativarPipelineCheckbox(boolean check) {
		boolean checkbox = checkboxPipeline.isSelected();
		System.out.println(checkbox + " 1");
		if(checkbox !=check) {
			checkboxPipeline.click();
		}
	}
	
	public List<String> getPipelinesCriadas(){
		List<String> pipelines = new ArrayList<String>();
		Select select = new Select(selectPipelines);
		for(WebElement e : select.getAllSelectedOptions()) {
			pipelines.add(e.getText());
		}
		return pipelines;
	}
	
	public boolean verificarExistenciaPipeline(String nome) {
		for(String n : getPipelinesCriadas()) {
			if(n.toLowerCase().trim().equals(nome.toLowerCase().trim())) {
				return true;
			}
		}
		return false;
	}
	
	public void selectPipeline(Object value) {
		if(value instanceof String) {
			String nome = (String) value;
			Select select = new Select(selectPipelines);
			if(opcaoExistenteNoSelect(select, nome)) {
				select.selectByVisibleText(nome);
			}else {
				System.err.println("Pipeline " + nome + " Não foi encontrado no Select");
			}
		}else if(value instanceof Integer) {
			int index = (int) value;
			Select select = new Select(selectPipelines);
			if(select.getOptions().size()>=index) {
				select.deselectByIndex(index);
			}else {
				System.err.println("Pipeline index: " + index + " Não foi encontrado no Select");
			}
		}
		
	}
	
	public void selectPipeline(String nomePipeline) {
		Select select = new Select(selectPipelines);
		if(opcaoExistenteNoSelect(select, nomePipeline)) {
			select.selectByVisibleText(nomePipeline);
		}else {
			System.err.println("Pipeline " + nomePipeline + " Não foi encontrado no Select");
		}
	}
	
	public void selectPipeline(int index) {
		Select select = new Select(selectPipelines);
		if(select.getOptions().size()>=index) {
			select.deselectByIndex(index);
		}else {
			System.err.println("Pipeline index: " + index + " Não foi encontrado no Select");
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
		
		String className = acordPDIs.getAttribute("class");
		if(className.equals("accordion-button collapsed")) {
			acordPDIs.click();
		}
		
		if(!listaDePDIs.isEmpty()) {
			for(WebElement e : listaDePDIs) {
//				if(e.getText().toLowerCase().equals(Nome.toLowerCase())) {
				if(e.getAttribute("innerText").toLowerCase().trim().equals(Nome.toLowerCase().trim())) {
					System.out.println("Entrei pdi");
					e.click();
					valid = true;
					break;
				}
			}
			if(!valid) {
				System.err.println("PDI "+ Nome + ", nao encontrado");
			}
		}else {
			System.err.println("Nenhum PDI Cadastrado");
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
			System.err.println("Nenhum PDI ou Pipeline foi adicionado a esta Pipeline");
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
		boolean checkbox = checkboxPipeline.isSelected();
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
	
	public Optional<WebElement> getParametroPDI(String value){
		for(WebElement e : ListaDeParametros) {
			if(e.findElement(By.xpath("label")).getText().toLowerCase().equals(value.toLowerCase())) {
				return Optional.of(e.findElement(By.xpath("input")));
			}
		}
		System.err.println("O Parametro: " + value + " Nao foi encontrado" );
		return Optional.empty();
	}
	
	public void inserirParametroTipoString(String nomeParam, String value) {
		Optional<WebElement> parametroRecuperado = getParametroPDI(nomeParam);
		
		if(parametroRecuperado.isPresent()) {
			WebElement parametro = parametroRecuperado.get();
			parametro.clear();
			parametro.sendKeys(value);
		}else {
			System.err.println("O parametro: " + nomeParam + " Nao foi encontrado" );
		}
		
	}
	
	public void inserirParametroTipoBoolean(String nomeParam, boolean value) {
		Optional<WebElement> parametroRecuperado = getParametroPDI(nomeParam);
		
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
	
	public boolean verificarParametroTipoString(String nomeParam, String value) {
		Optional<WebElement> parametroRecuperado = getParametroPDI(nomeParam);
	
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
}
