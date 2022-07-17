package api.slack;

import api.pojos.SlackSendPojo;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import utils.ConfigReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SlackSteps {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;
    final static String BEARER="Bearer ";

    @Before
    public void setUp(){
        RestAssured.baseURI= ConfigReader.getProperty("slackBaseUrl");
        requestSpecification=new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    @Given("{string} endpoint with token {string} post message as a user {string}")
    public void endpointWithTokenPostMessageAsAUser(String endPoint, String token, String user,String text) {
        SlackSendPojo slackSendPojo=new SlackSendPojo("C03Q4GJKYRF",text,user);
       response = given().spec(requestSpecification).header("Authorization",BEARER+ConfigReader.getProperty(token)).body(slackSendPojo)
                .when().post("https://slack.com/api/chat.postMessage").then().log().body().statusCode(HttpStatus.SC_OK).extract().response();

    }


    @Then("validate user is {string}")
    public void validateUserIs(String user) {
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(user,(String) jsonPath.get("message.username"));

    }

    @When("delete message")
    public void deleteMessage() {
        JsonPath jsonPath=response.jsonPath();
        String channel = jsonPath.get("channel");
        String ts = jsonPath.get("ts");
        Map<String,String> deleteBody = new HashMap<>();
        deleteBody.put("channel",channel);
        deleteBody.put("ts",ts);
       response = given().spec(requestSpecification).header("Authorization",BEARER+ConfigReader.getProperty("slackBearer")).body(deleteBody)
                .when().request("Post","\n" +
                        "https://slack.com/api/chat.delete").then().log().body(true).extract().response();
    }

    @Then("validate deleted message attribute {string}")
    public void validateDeletedMessageAttribute(String ok) {
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(true,jsonPath.get(ok));
    }

}
