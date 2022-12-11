package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"steps", "config"},
		tags = "@Servicos",
		plugin = {"pretty", "html:target/retalhos/Servicos.html", "json:target/retalhos/report-Servicos.json"},
		monochrome = true, 
		snippets = SnippetType.CAMELCASE, 
		dryRun = false
		)
public class RunnerServicos {

}
