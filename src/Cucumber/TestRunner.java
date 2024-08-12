package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/cucumber",glue="rsa.StepDefinitions",tags="@Errorvalidation",monochrome=true,plugin= {"html:taget/cucumber.html"})
public class TestRunner extends AbstractTestNGCucumberTests {

}
