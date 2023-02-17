package APIAssessment.Question2;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Files.EngageData.*;
import static io.restassured.RestAssured.given;

public class Scenario3 {

    int id;
    @Test
    void testPostRequest() {

        // Pass the correct data in all required fields in the body

        Pojo_Post data = new Pojo_Post();

        data.setName("Nishant Rawat");
        data.setTech_type_id(4);
        data.setDescription("description data");
        data.setDoc_link("https://jsonformatter.org/");
        data.setLogo(9);
        data.setAssoc_tags(asso_tags);

        Response res = given().headers("Authorization", "Bearer " + token)
            .contentType(CONTENT_TYPE)
            .body(data)
            .when().post(url);
        id = res.jsonPath().getInt("id");
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = {"testPostRequest"})
    void testDeleteRequest() {
        given().headers("Authorization", "Bearer " + token)
            .contentType(CONTENT_TYPE)
            .when().delete(url+"/" + id)
            .then().statusCode(200).log().all();
    }
}
