package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/Features/ToysPurchaseE2E.feature",
        glue = {"StepDefinition"}

)
public class TestRunner extends AbstractTestNGCucumberTests {
}
