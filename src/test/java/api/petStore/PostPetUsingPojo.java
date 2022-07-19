package api.petStore;

import api.pojos.PetPojo;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponseOptions;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class PostPetUsingPojo {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    ValidatableResponseOptions validatableResponseOptions;

    @Before
    public void setUp(){
        RestAssured.baseURI="https://petstore.swagger.io";
        RestAssured.basePath="/v2/pet";
        requestSpecification=new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
        responseSpecification=new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void postPet(){//Using Pojo
        PetPojo pet = new PetPojo(122,"Hatiko","alive");
        given().spec(requestSpecification).body(pet)
                .when().post()
                .then().spec(responseSpecification).log().all();
    }

    @Test
    public void postPet2(){//Using Java Generics(Map0
        Map<String,Object> pet = new HashMap<>();
        pet.put("name",new Faker().dog().name());
        pet.put("age",7);
        pet.put("status","alive");
        pet.put("id",new Random().nextInt(1000));
        pet.put("photoUrls",new String[]{"https://mvnrepository.com/artifact/io.rest-assured/rest-assured/5.1.1", "techtorial.com"});


        given().spec(requestSpecification).body(pet)
                .when().post()
                .then().spec(responseSpecification).log().all(true);
    }

    @Test
    public void getPet(){
        Response response=given().accept(ContentType.JSON).when().get("https://petstore.swagger.io/v2/pet/122");
        response.then().statusCode(HttpStatus.SC_OK).log().body();
        Map responseBody = response.as(Map.class);
        Assert.assertEquals(122,responseBody.get("id"));
    }





}
