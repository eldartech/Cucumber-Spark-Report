package api.reqres;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class GroovyPractice {
    @Before
    public void setUp(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.basePath="api/unknown";
    }

    @Test
    public void getListTest(){
        given().when().get()
                .then().statusCode(HttpStatus.SC_OK)
                .body("page", is(1),
                        "data.size()",is(6)
                )
        ;
    }


    @Test
    public void getListTest2(){

        int expectedPerPage = given().when().get("https://reqres.in/api/users?page=2")
                .then().statusCode(HttpStatus.SC_OK).extract().path("per_page");



        given().when().get("https://reqres.in/api/users?page=2")
                .then().statusCode(HttpStatus.SC_OK)
                .body("page", is(2),
                        "data.size()",is(expectedPerPage),
                        "data.findAll { it.avatar.startsWith('https://')}.size()",is(expectedPerPage)

                )
        ;
    }
}
