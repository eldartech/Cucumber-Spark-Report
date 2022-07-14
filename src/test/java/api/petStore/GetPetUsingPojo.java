package api.petStore;

import api.pojos.PetPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetPetUsingPojo {

    @Test
    public void getPet(){
        Response response=given().accept(ContentType.JSON).when().get("https://petstore.swagger.io/v2/pet/123");
        response.then().statusCode(HttpStatus.SC_OK).log().body().assertThat().body("tags.id[1]", Matchers.equalTo(3));
        PetPojo responseBody = response.as(PetPojo.class);
        Assert.assertEquals(123,responseBody.getId());
        System.out.println(responseBody.getCategory().getId());
        System.out.println(responseBody.getTags().size());
        System.out.println(responseBody.getTags().get(1).get("name"));
    }


}
