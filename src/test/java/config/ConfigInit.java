package config;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class ConfigInit {

	public static WebDriver driver;
	
	public ConfigInit() {}
	
	public static void acessarSistema() {
		System.setProperty("webdriver.chrome.driver","/home/jordielson/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("http://localhost:3000");
	}
	
	public static void acessarSistema(String URL) {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(URL);

	}
	
	public static <T> T Na(Class<T>classe){
		return PageFactory.initElements(driver, classe);
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
