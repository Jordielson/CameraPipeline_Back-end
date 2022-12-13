package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CameraPage {

	@FindBy(xpath = "//*[@id=\"button-add-camera\"]")
	private WebElement botaoAdicionarNovaCamera;
	
	@FindBy(xpath = "//*[@placeholder=\"Encontrar câmera\"]")
	private WebElement campoPesquisaCamera;

	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[2]/form/*/input[@id=\"formBasicName\"]")
	private WebElement campoCardNome; 
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[2]/form/*/input[@id=\"formBasicUrl\"]")
	private WebElement campoCardURL;
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[2]/form/*/*/*/*/input[@id=\"inline-radio-1\"]")
	private WebElement RadioCardPrivadaSim;
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[2]/form/*/*/*/*/input[@id=\"inline-radio-2\"]")
	private WebElement RadioCardPrivadaNão;
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[2]/form/*/*/*/input[@id=\"formBasicCoordinates\" and @placeholder=\"Latitude\"]")
	private WebElement campoCardCoordenadasLatitude;

	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[2]/form/*/*/*/input[@id=\"formBasicCoordinates\" and @placeholder=\"Longitude\"]")
	private WebElement campoCardCoordenadasLongitude;
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[2]/form/*/input[@id=\"formBasicLimiterFPS\"]")
	private WebElement campoCardLimitadorFPS;
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/div[2]/form/*/button[@id=\"button-addon2\"]")
	private WebElement botaoCardSalvarCardCamera;
	
	@FindBy(xpath = "//*[@class=\"modal-content\"]/*/[@class=\"btn-close\"]")
	private WebElement botaoFechar;
	
	@FindBy(xpath = "//*[@class=\"modal-dialog\"]/div")
	private WebElement modalExcluirCamera;
	
	@FindBy(xpath = "//*[@class=\"mx-4 mt-4 mb-1 listCamera list-group\"]/div[@class=\"list-group-item list-group-item-light\"]")
	private List<WebElement> cameras;
	
	
	public void clicarVisualizarCamera(String nomeCamera) {
		for(WebElement e : cameras) {
			String nomeDaCamera = e.getText();
			if(nomeDaCamera.equals(nomeCamera)) {
				e.findElement(By.xpath("div/button[@title=\"visualizar\"]")).click();;
				break;
			}
		}
	}
	
	public void clicarEditarCamera(String nomeCamera) {
		for(WebElement e : cameras) {
			String nomeDaCamera = e.getText();
			if(nomeDaCamera.equals(nomeCamera)) {
				e.findElement(By.xpath("div/button[@title=\"EDITAR\"]")).click();;
				break;
			}
		}
	}
	
	public void clicarAtiviarDesativarCamera(String nomeCamera) {
		for(WebElement e : cameras) {
			String nomeDaCamera = e.getText();
			if(nomeDaCamera.equals(nomeCamera)) {
				e.findElement(By.xpath("div/form/div/input[@id=\"custom-switch\"]")).click();;
				break;
			}
		}
	}

	public void clicarExcluirCamera(String nomeCamera) {
		for(WebElement e : cameras) {
			String nomeDaCamera = e.getText();
			if(nomeDaCamera.equals(nomeCamera)) {
				e.findElement(By.xpath("div/button[@title=\"EXCLUIR\"]")).click();;
				setModalExclirServico("Excluir");
				break;
			}
		}
	}
	
	public void deletarTodasAsCameras() {
		for(WebElement e : cameras) {
			e.findElement(By.xpath("div/button[@title=\"EXCLUIR\"]")).click();;
			setModalExclirServico("Excluir");
		}
	}
	
	public int getTotalCameras() {
		return cameras.size();
	}
	
	public boolean cameraIsPresente(String nome) {
		for(WebElement e : cameras) {
			String nomeDaCamera = e.getText();
			if(nomeDaCamera.equals(nome)) {
				return true;
			}
		}
		return false;
	}
	
	public void clicarBotaoAdicionarNovaCamera() {
		botaoAdicionarNovaCamera.click();
	}
	
	public void InserirCampoPesquisaCamera(String value) {
		campoPesquisaCamera.clear();
		campoPesquisaCamera.sendKeys(value);
	}
	
	public String getCampoPesquisaCamera() {
		return campoPesquisaCamera.getAttribute("value");
	}
	
	public void inserirCampoCardNomeCamera(String value) {
		campoCardNome.clear();
		campoCardNome.sendKeys(value);
	}
	
	public String getCampoCardNomeCamera() {
		return campoCardNome.getAttribute("value");
	}
	
	public void inserirCampoCardUrl(String value) {
		campoCardURL.clear();
		campoCardURL.sendKeys(value);
	}
	
	public String getCampoCardUrl() {
		return campoCardURL.getAttribute("value");
	}
	
	public void clicarRadioCardPrivadaSim() {	
		RadioCardPrivadaSim.click();
	}
	
	public boolean isRadioCardPrivadaSim() {
		return RadioCardPrivadaSim.isSelected();
	}
	
	public void clicarRadioCardPrivadaNao() {
		RadioCardPrivadaNão.click();
	}
	
	public boolean isRadioCardPrivadaNao() {
		return RadioCardPrivadaNão.isSelected();
	}
	
	public void inserirCampoCardCoordenadasLatitude(String value) {
		campoCardCoordenadasLatitude.clear();
		campoCardCoordenadasLatitude.sendKeys(value);
	}
	
	public String getCampoCardCoordenadasLatitude() {
		return campoCardCoordenadasLatitude.getAttribute("value");
	}
	
	public void inserirCampoCardCoordenadasLongitude(String value) {
		campoCardCoordenadasLongitude.clear();
		campoCardCoordenadasLongitude.sendKeys(value);
	}
	
	public String getCampoCardCoordenadasLongitude() {
		return campoCardCoordenadasLongitude.getAttribute("value");
	}
	
	public void inserirCampoCardLimitadorFPS(String value ) {
		campoCardLimitadorFPS.clear();
		campoCardLimitadorFPS.sendKeys(value);
	}
	
	public String getCampoCardLimitadorFPS() {
		return campoCardLimitadorFPS.getAttribute("value");
	}
	
	public void clicarBotaoCardSalvar() {
		botaoCardSalvarCardCamera.click();
	}
	
	public void clicarBotaoFechar() {
		botaoFechar.click();
		botaoFechar.sendKeys(Keys.F5);
	}
	
	public void setModalExclirServico(String value) {
		switch (value) {
		
		case "Fechar":
			modalExcluirCamera.findElement(By.xpath("div[1]/button")).click();;
			break;
			
		case "Cancelar":
			modalExcluirCamera.findElement(By.xpath("div[3]/button[text() = \"Cancelar\"]")).click();;
			break;
			
		case "Excluir":
			modalExcluirCamera.findElement(By.xpath("div[3]/button[text() = \"Excluir\"]")).click();;
			break;

		default:
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
