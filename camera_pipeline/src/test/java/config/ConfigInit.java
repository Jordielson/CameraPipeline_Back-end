package config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class ConfigInit {

	public static WebDriver driver;
	
	public ConfigInit() {}
	
	public static void acessarSistema() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\plgon\\git\\CameraPipeline_Back-end\\camera_pipeline\\driver\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("http://localhost:3000");
	}
	
	public static void acessarSistema(String URL) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\plgon\\git\\CameraPipeline_Back-end\\camera_pipeline\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get(URL);

	}
	
	public static <T> T Na(Class<T>classe){
		return PageFactory.initElements(driver, classe);
	}
}
