package com.dlorenc.examples.bdd.jbehave.steps;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.ScenarioType;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.dlorenc.examples.bdd.jbehave.rest.AccountResource;
import com.dlorenc.examples.bdd.jbehave.rest.AccountRestService;
import com.jayway.restassured.response.Response;

public class RestAccountSteps {

    private AccountRestService accountRestService;
    private Response postStatusCode;

    @AfterScenario(uponType=ScenarioType.NORMAL)
    public void afterEachScenario() throws Exception {
        stopRestService();
    }

    @AfterScenario(uponType=ScenarioType.EXAMPLE)
    public void afterEachExample() throws Exception {
        stopRestService();
    }

    private void stopRestService() throws Exception {
        if (accountRestService != null) {
            accountRestService.stop();
            accountRestService = null; 
        }
        AccountResource.reset();
    }
    
    @Given("an account REST service")
    public void startRestService() {
        accountRestService = new AccountRestService();
        accountRestService.start();
        when()
            .get("/isAlive")
        .then()
            .statusCode(200)
            .body(equalTo("I'm alive"));
    }
    
    @Given("an empty account")
    public void checkEmpty() {
        checkBalance("/account", BigDecimal.ZERO);
    }

    @When("I POST to $path amount $amount")
    public void post(String path, BigDecimal amount) {
        postStatusCode = when()
                            .post(path + "/" + amount)
                            .thenReturn();
    }

    @Then("GET request to $path returns balance $expectedBalance")
    public void checkBalance(String path, BigDecimal expectedBalance) {
        when()
            .get(path)
        .then()
            .statusCode(200)
            .body(equalTo(expectedBalance.toString()));
    }
    
    @Then("$expectedStatusCode status code is returned")
    public void checkPostStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, postStatusCode.statusCode());
    }
    
}
