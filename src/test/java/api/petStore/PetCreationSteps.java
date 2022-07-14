package api.petStore;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetCreationSteps {


    RequestSpecification requestSpecification;
    Response response;



    @Given("user provides request specifications")
    public void userProvidesRequestSpecifications() {
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="/v2/pet";
        requestSpecification= given().accept(ContentType.JSON).contentType(ContentType.JSON);

    }

    @When("user creates a pet")
    public void userCreatesAPet() {//Using Json File
        File file=new File("src/test/java/api/petStore/petCreation.json");
       response= requestSpecification.body(file).post();
    }

    @Then("user validates status code of {int}")
    public void userValidatesStatusCodeOf(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("user validates response body")
    public void userValidatesResponseBody() {
        response.then().body("name", Matchers.equalTo("Bobo"));
        Map responseBody = response.as(Map.class);
        Assert.assertEquals("Bobo",responseBody.get("name").toString());

    }
}
