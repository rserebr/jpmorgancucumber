package hooks;

import environment.Environment;
import environment.LocalEnvironment;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import java.io.IOException;

public class ApiHooks {

    private Environment environment;

    public ApiHooks() throws IOException {
        this.environment = new LocalEnvironment();
    }

    @Before
    public void beforeEachScenario() {
        RestAssured.baseURI = environment.getBaseURI();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

}
