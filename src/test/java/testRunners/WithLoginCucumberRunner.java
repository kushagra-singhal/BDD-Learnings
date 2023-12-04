package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/WithLogin.feature", glue = {"stepDefinitions.withLogin", "testRunners"}, monochrome = true, plugin = {"html:target/cucumber.html"})
public class WithLoginCucumberRunner extends AbstractTestNGCucumberTests {
}