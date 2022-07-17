package api.slack;

import io.cucumber.java.bs.A;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.ConfigReader;

import java.util.*;

import static io.restassured.RestAssured.given;

public class ConversationHistory {
    RequestSpecification requestSpecs;
    ResponseSpecification responseSpecs;
    Response response;
    final static String AUTHORIZATION="Authorization";
    final static String BEARER = "Bearer ";

    @Before
    public void setUp(){
        RestAssured.baseURI="https://slack.com";
        RestAssured.basePath="api/conversations.history";
        requestSpecs = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
        responseSpecs = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }
    @Test
    public void conversationHistoryTest(){
        Response response = given().spec(requestSpecs).header(AUTHORIZATION,BEARER+ ConfigReader.getProperty("slackBearer"))
                .when().queryParam("channel","C03Q4GJKYRF").get().then().spec(responseSpecs).extract().response();
        Map<String,Object> responseBody = response.getBody().as(new TypeRef<Map<String, Object>>() {});
        List<Map<String,Object>> messages = (List<Map<String, Object>>) responseBody.get("messages");
        Map<String,List<String>> messagesPerPerson = new HashMap<>();
        for (Map<String,Object> message:messages){
            if (message.get("username")!=null) {
                if (!messagesPerPerson.containsKey(message.get("username"))) {
                    List<String> msg = new ArrayList<>();
                    msg.add(message.get("text").toString());
                    messagesPerPerson.put(message.get("username").toString(), msg);
                } else {
                    messagesPerPerson.get(message.get("username")).add(message.get("text").toString());
                }
            }
        }

        Assert.assertEquals(Arrays.asList("HI", "HI", "hi", "hi"),messagesPerPerson.get("Fotima"));

    }
}
