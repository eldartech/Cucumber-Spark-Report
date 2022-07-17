package api.swapi;

import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RootApi {

    @Test
    public void getRootApi() {
        String peopleURL = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().get("https://swapi.dev/api/").then().statusCode(HttpStatus.SC_OK)
                .extract().path("people");

        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().get(peopleURL).then().statusCode(HttpStatus.SC_OK).log().body(true);
    }


    public String getSWAPI_URI(String endPoint) {
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().get("https://swapi.dev/api/").then().statusCode(HttpStatus.SC_OK)
                .extract().path(endPoint);
    }

    @Test
    public void getPlanets(){
        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().get(getSWAPI_URI("planets")).then().statusCode(HttpStatus.SC_OK).extract().response();

        Map<String, Object> body = response.getBody().as(new TypeRef<Map<String, Object>>() {});
        List<Map<String,Object>> results = (List<Map<String, Object>>) body.get("results");
        Map<String, String> planetsPopulation = new LinkedHashMap<>();
        for (int i = 0; i<results.size();i++){
            planetsPopulation.put((String)results.get(i).get("name"),results.get(i).get("population").toString());
        }
        System.out.println(planetsPopulation);
    }


    @Test
    public void getStarships(){
        int count = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().get(getSWAPI_URI("starships"))
                .then().extract().path("count");

        Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().get(getSWAPI_URI("starships"))
                .then().statusCode(HttpStatus.SC_OK).body("results.size()", Matchers.is(10)).extract().response();

        Map<String,Object> body = response.getBody().as(new TypeRef<Map<String, Object>>() {});
        List<Map<String,Object>> results = (List<Map<String, Object>>) body.get("results");
        Map<String, String> starshipManufacturer = new LinkedHashMap<>();
        for (int i = 0; i<results.size();i++){
            starshipManufacturer.put((String)results.get(i).get("name"),results.get(i).get("manufacturer").toString());
        }
        System.out.println(starshipManufacturer);


    }

}
