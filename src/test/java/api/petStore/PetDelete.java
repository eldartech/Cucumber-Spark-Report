package api.petStore;

import api.pojos.PetPojo;
import com.github.javafaker.Faker;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class PetDelete {

    @Test
    public void deletePetTest(){

        Random random=new Random();
        int id = random.nextInt(1000);
        Faker faker=new Faker();
        String dogName = faker.dog().name();

        PetPojo pet = new PetPojo(id,dogName,"alive");
        given().contentType(ContentType.JSON).accept(ContentType.JSON).body(pet)
                .when().request("Post","https://petstore.swagger.io/v2/pet")
                .then().contentType(ContentType.JSON).log().body(false);

        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().request("Get","https://petstore.swagger.io/v2/pet/"+id)
                .then().log().body(false).assertThat().body("name",Matchers.equalTo(dogName));

        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().request("delete","https://petstore.swagger.io/v2/pet/"+id)
                .then().statusCode(HttpStatus.SC_OK).log().body()
                .assertThat().body("message", Matchers.is(Integer.toString(id)))
        ;

        Map<String,Object> response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .when().request("Get","https://petstore.swagger.io/v2/pet/"+id)
                .then().statusCode(HttpStatus.SC_NOT_FOUND).extract().as(new TypeRef<Map<String, Object>>() {});
        Assert.assertEquals("Pet not found",response.get("message"));

    }
}
