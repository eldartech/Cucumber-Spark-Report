package api.slack;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.ConfigReader;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SlackTest {
    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;
    Response response;
    @Before
    public void setUp(){
        RestAssured.baseURI="https://slack.com";
        RestAssured.basePath="api/conversations.list";
        requestSpecification=new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();

        responseSpecification=new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .build();

    }
    @Test
    public void conversationListTest(){
        response = given().spec(requestSpecification).header("Authorization","Bearer "+ ConfigReader.getProperty("slackBearer"))
                .when().get().then().spec(responseSpecification).extract().response();

        List<String> names= response.path("channels.findAll{it}.name");
        Assert.assertEquals(Arrays.asList("general", "random", "sdet", "studentsdiscussion", "homework", "api"),names);

        List<String> names2 = response.path("channels.findAll{it.num_members<15}.name");
        Assert.assertEquals(Arrays.asList("studentsdiscussion", "homework", "api"),names2);



    }
}
