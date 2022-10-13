package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"steps", "config"},
		tags = "@EdicaoImagem",
		plugin = {"pretty", "html:target/retalhos/Edicao_Imagem.html", "json:target/retalhos/report-Edicao_Imagem.json"},
		monochrome = true, 
		snippets = SnippetType.CAMELCASE, 
		dryRun = false
		)
public class RunnerEdicaoImagem {

}
