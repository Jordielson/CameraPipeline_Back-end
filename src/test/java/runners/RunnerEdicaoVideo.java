package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"steps", "config"},
		tags = "@EdicaoVideo",
		plugin = {"pretty", "html:target/retalhos/Edicao_Video.html", "json:target/retalhos/report-Video_Imagem.json"},
		monochrome = true, 
		snippets = SnippetType.CAMELCASE, 
		dryRun = false
		)
public class RunnerEdicaoVideo {

}
