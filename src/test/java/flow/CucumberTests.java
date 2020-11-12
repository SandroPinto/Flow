package flow;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:email.feature",
        glue = "classpath:flow",
        plugin = "html:target/cucumber-tests-reports"
)
public class CucumberTests {
}
