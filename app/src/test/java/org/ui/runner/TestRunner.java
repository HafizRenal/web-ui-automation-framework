package org.ui.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features   = "src/test/resources/features",
        glue       = "org.ui.steps",
        plugin     = {
                "pretty",
                "html:target/cucumber-report/report.html"
        },
        monochrome = true
)
public class TestRunner {
}