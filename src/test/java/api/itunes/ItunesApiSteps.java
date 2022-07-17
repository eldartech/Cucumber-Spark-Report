package api.itunes;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import utils.ConfigReader;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class ItunesApiSteps {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;

    @Before
    public void setUp(){
        RestAssured.baseURI = ConfigReader.getProperty("itunes");
        RestAssured.basePath = "us/search";
        requestSpecification=new RequestSpecBuilder()
                .setContentType("text/javascript; charset=utf-8")
                .build();
        responseSpecification=new ResponseSpecBuilder()
                .expectContentType("text/javascript; charset=utf-8")
                .build();

    }

    @Given("endpoint with query parameter of {string}")
    public void endpointWithQueryParameterOf(String artist) {
        response=given().spec(requestSpecification).queryParam("term",artist.replaceAll(" ","%20"))
                .when().get()
                .then().log().body()
                .extract().response();

    }

    @Then("validate status code is {int}")
    public void validateStatusCodeIs(int statusCode) {
        Assert.assertEquals(statusCode,response.getStatusCode());
    }

    @And("validate content type is {string}")
    public void validateContentTypeIs(String contentType) {
        Assert.assertEquals(contentType,response.getContentType());
    }
}
