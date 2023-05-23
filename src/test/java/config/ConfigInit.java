package config;

import static config.ConfigInit.driver;
import static config.ConfigInit.esperar;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

public class ConfigInit {

	public static WebDriver driver;

	public ConfigInit() {
	}

	public static void acessarSistema() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriverV113.exe");
		driver = new ChromeDriver(gerarChromeOptions());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:3000");
		
//		threadCapturarNotificacoesDispensaveis();
	}

	public static void acessarSistema(String URL) {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriverV113.exe");
		driver = new ChromeDriver(gerarChromeOptions());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(URL);
		
//		threadCapturarNotificacoesDispensaveis();

	}
	
	private static ChromeOptions gerarChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		chromeOptions.addArguments("--remote-allow-origins=*");
		return chromeOptions;
	}

	public static void alterarJanela(String URL) {

		String parentWindow = driver.getWindowHandle();

		driver.get(URL);

		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
//		          driver.close(); //closing child window
//		          driver.switchTo().window(parentWindow); //cntrl to parent window
			}
		}
	}

//	private static void threadCapturarNotificacoesDispensaveis() {
//		Thread ouvinte = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				do {
//					try {
//						WebElement esperado = driver.findElement(By.id("toastMsg"));
//						String erroRecuperado = esperado.getAttribute("innerText");
//						
//						if (erroRecuperado.equals("Acorreu um erro ao gerar um resultado")) {
//							WebElement botaoFechar = driver.findElement(By.xpath(
//									"//*[@class=\"Toastify__close-button Toastify__close-button--light\" or @class=\"Toastify__close-button Toastify__close-button--colored\" and @innerText=\"Acorreu um erro ao gerar um resultado\"]"));
//							botaoFechar.click();
//						}
//						esperar(2);
//					} catch (org.openqa.selenium.NoSuchElementException e) {
//						esperarMS(250);
//					}
//				} while (true);
//			}
//		});
//		
//		ouvinte.start();
//		
//	}

	public static <T> T Na(Class<T> classe) {
		return PageFactory.initElements(driver, classe);
	}

	public static void aceitarAlerta() {
		try {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("POPUP AXIOS");
		}

	}

	public static void esperar(int segundos) {
		int seg = segundos * 1000;
		try {
			Thread.sleep(seg);
		} catch (InterruptedException e) {
			System.out.println("Deu pau no esperar() " + e.getStackTrace());
		}
	}

	public static void esperarMS(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			System.out.println("Deu pau no esperarMS() " + e.getStackTrace());
		}
	}
}
