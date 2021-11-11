package com.example.MCardSpring;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber.json"},
        glue = {"com.example.MCardSpring.StepDefs"},
        features = "src/test/Features/"
)
public class CucumberIntegrationTest {
}
