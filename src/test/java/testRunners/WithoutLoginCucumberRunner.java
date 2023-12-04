package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features/WithoutLogin.feature",tags = "@smoke or @sanity",glue = {"stepDefinitions.withoutLogin", "testRunners"}, monochrome = true, plugin = {"html:target/cucumber.html"})
public class WithoutLoginCucumberRunner extends AbstractTestNGCucumberTests {
}
