package api.footballApi;

import api.pojos.FootballCompetitionsPojo;
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
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class FootballApiCompetitions {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    Response response;

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://api.football-data.org";
        RestAssured.basePath = "v2/competitions";
        requestSpecification  = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .build();
        response = given().spec(requestSpecification)
                .when().get()
                .then().spec(responseSpecification)
                .extract().response();
    }

    @Test
    public void getCompetitions(){//POJO
       FootballCompetitionsPojo compDes =  response.getBody().as(FootballCompetitionsPojo.class);
        Assert.assertEquals("Count of Competition Test Failed",compDes.getCount(),compDes.getCompetitions().size());
        List<Map<String,Object>> competitionList = compDes.getCompetitions();
        List<String> competions_2100 = new ArrayList<>();
        for (Map<String,Object> competition:competitionList){
           if ((int)competition.get("id") >= 2100){
               competions_2100.add(competition.get("name").toString());
           }
        }
        Assert.assertTrue(competions_2100.contains("Puchar Polski"));
    }

    @Test
    public void getCompetitionsJsonPath(){//JsonPath
        JsonPath jsonPath = response.jsonPath();
        List<Map<String,Object>> competitionsList = jsonPath.getList("competitions");
        Assert.assertEquals("Count of Competition Test Failed",(int)jsonPath.get("count"),competitionsList.size());
        List<String> competions_2100 = new ArrayList<>();
        for (Map<String,Object> competition:competitionsList){
            if ((int)competition.get("id") >= 2100){
                competions_2100.add(competition.get("name").toString());
            }
        }
        Assert.assertTrue(competions_2100.contains("Puchar Polski"));
    }

    @Test
    public void getCompetitionsGroovy(){//Groovy
        List<String> competitionsList = response.path("competitions.findAll { it.id>=2100}.name");
        Assert.assertTrue(competitionsList.contains("Puchar Polski"));

        List<String> competitionListMexico = response.path("competitions.findAll { it.area.name=='Mexico'}.name");
        System.out.println(competitionListMexico);
        List<String> expectedMexicoCompetitions = Arrays.asList("SuperCopa MX", "Copa MX", "Liga MX");
        Assert.assertEquals(expectedMexicoCompetitions,competitionListMexico);
    }

}
