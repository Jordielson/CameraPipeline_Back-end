package config;


import io.cucumber.java.After;
import io.cucumber.java.Before;

import static config.ConfigInit.*;

import org.apache.tomcat.jni.Time;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

public class Hooks {

	
	@Before(value = "not @CadastroUser")
	public void setUp() {
		acessarSistema();
	}
	
	@Before(value = "@CadastroUser")
	public void setUpCadastroUser() {
		acessarSistema("http://localhost:3000/criar-conta");
	}
	
	
	
	
	@After
	public void tearDown() {
		esperar(1);
		driver.close();
		driver.quit();
	}
}
