package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
features = "Features",tags="@github",
 glue={"stepdefinition","hook"},
 monochrome=true,
 plugin = { "pretty", "html:target/cucumber-reports","rerun:Features/rerun.txt" }
 )
public class TestRunner {
	
	
	

}
