package APIAssessment.Question3;

import APIAssessment.Question2.Pojo_Post;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Files.EngageData.*;
import static io.restassured.RestAssured.given;

public class Scenario4and5 {
    //Update Name, and Description from existing data in the body

    int id;
    Pojo_Post data = new Pojo_Post();

    @Test
    void testPostRequest() {
        data.setName("Nishant Rawat");
        data.setTech_type_id(4);
        data.setDoc_link("https://jsonformatter.org/");
        data.setDescription("description data");
        String[] asso_tags = {"Framework", "Angular", "Java"};
        data.setAssoc_tags(asso_tags);
        data.setLogo(9);

        id = given().headers("Authorization", "Bearer " + token)
            .contentType(CONTENT_TYPE)
            .body(data)
            .when().post(url).jsonPath().getInt("id");
        System.out.println(id);
    }

    @Test(dependsOnMethods = {"testPostRequest"}, priority = 2)
    void testPutRequest() {
        data.setName("ExampleAA");
        data.setTech_type_id(4);
        data.setDoc_link("https://jsonformatter.org/");
        data.setDescription("description data valuee");
        data.setAssoc_tags(asso_tags);
        data.setLogo(9);

        given().headers("Authorization", "Bearer " + token)
            .contentType(CONTENT_TYPE)
            .body(data)
            .when().put(url+"/" + id)
            .then().statusCode(200).log().all();
    }

    @Test(dependsOnMethods = {"testPutRequest"}, priority = 3)
    void testGetData() {
        Response res = given().headers("Authorization", "Bearer " + token).contentType(CONTENT_TYPE)
            .when().get(url+"/" + id);
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().get("name"), "ExampleAA");
        Assert.assertEquals(res.jsonPath().get("tech_description"), "description data valuee");
    }

    @Test(dependsOnMethods = {"testPostRequest"}, priority = 4)
    void testDeleteRequest() {
        given().headers("Authorization", "Bearer " + token)
            .contentType(CONTENT_TYPE)
            .when().delete(url+"/" + id)
            .then().statusCode(200).log().all();
    }
}
