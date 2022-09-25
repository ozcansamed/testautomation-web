package com.abnamro.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {"html:target/failed-html-report"},
        glue = "com/abnamro/step_definitions",
        features = "@target/rerun.txt"
        // tags = "@failedToTest"
)

public class FailedTestRunner {

}