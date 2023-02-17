package APIAssessment.Question4;

import APIAssessment.Question2.Pojo_Post;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Files.EngageData.*;
import static io.restassured.RestAssured.given;

public class Scenario1and2 {

    //Pass the id in the DELETE request
    int id;
    Pojo_Post data = new Pojo_Post();

    @Test
    void testPostRequest() {
        data.setName("Nishant Rawat");
        data.setTech_type_id(4);
        data.setDescription("description dataa");
        data.setDoc_link("https://jsonformatter.org/");
        data.setLogo(9);
        String[] asso_tags = {"Framework", "Angular", "Java"};
        data.setAssoc_tags(asso_tags);

        id = given().headers("Authorization", "Bearer " + token)
            .contentType(CONTENT_TYPE)
            .body(data)
            .when().post(url).jsonPath().getInt("id");
        System.out.println(id);
    }

    @Test(dependsOnMethods = {"testPostRequest"}, priority = 2)
    void testDeleteRequest() {
        given().headers("Authorization", "Bearer " + token)
            .contentType(CONTENT_TYPE)
            .when().delete(url+"/" + id)
            .then().statusCode(200).log().all();
    }

    @Test(dependsOnMethods = {"testDeleteRequest"}, priority = 3)
    void testGetData() {
        Response res = given().headers("Authorization", "Bearer " + token).contentType(CONTENT_TYPE)
            .when().get(url+"/" + id);
        System.out.println(res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 404);
    }
}
