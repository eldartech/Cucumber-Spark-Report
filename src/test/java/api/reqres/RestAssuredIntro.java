package api.reqres;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class RestAssuredIntro {
    @Test
    public void getTest1(){
        String endPoint= "https://reqres.in/api/users?page=2";
        Response response = RestAssured.get(endPoint);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200,statusCode);
        System.out.println(response.asString());
        System.out.println(response.getContentType());
        Assert.assertEquals("application/json; charset=utf-8",response.getContentType());
        System.out.println(response.getTime());
    }

    @Test
    public void getTest2(){
        String endPoint= "https://reqres.in/api/users?page=2";
        given()
                .when().get(endPoint).then().statusCode(200).contentType("application/json; charset=utf-8")
                .log().all()
                .assertThat()
                .body("page",equalTo(2))
                .body("per_page",lessThanOrEqualTo(6))
                .body("total",greaterThanOrEqualTo(10))
                .body("total_pages",isA(Integer.class))
                .body("data.id",hasItem(7),
                "data.size()",is(6))
                .body("data.id", hasItems(7, 8, 9, 10, 11, 12))
                .body("data.first_name[0]",is("Michael"))
        ;
    }

    @Test  //Deserialization to Java Generics
    public void getTest3(){
       Map<String,Map<String,Object>> response = given()
                .when().get("https://reqres.in/api/users/8")
                .as(Map.class);
//        System.out.println(response);
//        System.out.println(response.get("data").get("id"));
//
//        System.out.println(response.get("support").get("text"));
        Assert.assertEquals("Lindsay Ferguson", response.get("data").get("first_name").toString()+" "+response.get("data").get("last_name"));
    }

    @Test
    public void postTest(){
        String body = "{\n" +
                "    \"name\": \"David\",\n" +
                "    \"job\": \"sdet\"\n" +
                "}";
        Response response = given().body(body).when().post("https://reqres.in/api/users");

        response.then()
                .log().body()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
//                .body("name",equalTo("David"))
//                .body("job",is("sdet"))
                .body("id",notNullValue())
                .body("createdAt",notNullValue());
    }


    @Test
    public void postTest2(){
        Response response = given().body(Payloads.newUser("Anna","QA Analyst")).when().post("https://reqres.in/api/users");

        response.then()
                .log().body()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
//                .body("name",equalTo("David"))
//                .body("job",is("sdet"))
                .body("id",notNullValue())
                .body("createdAt",notNullValue());
    }


    @Test
    public void putTest(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "api/users/2";
        File userJson = new File("src/test/java/api/reqres/newUser.json");
        given().body(userJson).when().put()
                .then().log().body().assertThat().body("updatedAt", Matchers.containsString("2022-07-12"));

    }




}
