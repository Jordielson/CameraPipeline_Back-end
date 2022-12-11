package config;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class ConfigInit {

	public static WebDriver driver;
	
	public ConfigInit() {}
	
	public static void acessarSistema() {
		System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriverV107.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("http://localhost:3000");
	}
	
	public static void acessarSistema(String URL) {
		System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriverV107.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(URL);

	}
	
	public static void alterarJanela(String URL) {
		
		String parentWindow = driver.getWindowHandle();
		
		driver.get(URL);
		
		Set<String> handles =  driver.getWindowHandles();
		   for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow)){
		          driver.switchTo().window(windowHandle);
//		          driver.close(); //closing child window
//		          driver.switchTo().window(parentWindow); //cntrl to parent window
		          }
		       }
	}
	
	public static <T> T Na(Class<T>classe){
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
}
