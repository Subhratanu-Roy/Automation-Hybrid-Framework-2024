package cucumberExe;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumberExe", glue = "SeleniumPractice.SeleniumHybridFrameworkNew.stepdefinitions", 
monochrome = true, tags = "@Regression", plugin = {
		"html:target/cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
