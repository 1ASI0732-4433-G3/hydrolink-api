package com.hydrolink.api;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // solo si está en src/test/resources/features
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.hydrolink.api.monitoring.bdd")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class DeviceCucumberTestRunner {
}

