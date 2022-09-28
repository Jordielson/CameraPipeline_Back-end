package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"steps", "config"},
		tags = "@Geral",
		plugin = {"pretty", "html:target/principal/index.html", "json:target/report.json"},
		monochrome = true, 
		snippets = SnippetType.CAMELCASE, 
		dryRun = false
		)
public class RunnerPrincipal {

	
}
