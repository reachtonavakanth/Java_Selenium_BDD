package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

public class POCTestRunner {

	@CucumberOptions(features = { "resources/appfeatures" }, glue = { "stepdefinitions",
			"ApplicationHooks" }, tags = "@sort or @navigate", plugin = { "pretty", "html:HTMLReports/MyHtml.html",

	})

	public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

	}
}
