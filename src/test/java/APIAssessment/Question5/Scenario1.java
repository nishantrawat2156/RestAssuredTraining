package APIAssessment.Question5;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Files.EngageData.*;
import static io.restassured.RestAssured.given;

public class Scenario1 {

    int id;
    JSONObject data = new JSONObject();

    @Test
    void testPostRequest() {

        // Pass the correct data in all required fields in the body

        data.put("name", "Nishant Rawat");
        data.put("tech_type_id", 4);
        data.put("doc_link", "https://jsonformatter.org/");
        data.put("description", "description dataa");
        String[] tags = {"Framework", "Angular", "Java"};
        data.put("assoc_tags", tags);
        data.put("logo", 9);

        id = given().headers("Authorization", "Bearer " + token)
            .contentType(CONTENT_TYPE)
            .body(data.toString())
            .when().post(url).jsonPath().getInt("id");
        System.out.println(id);
    }

    @Test(dependsOnMethods = {"testPostRequest"}, priority = 2)
    void testGetData() {
        Response res = given().headers("Authorization", "Bearer " + token).contentType(CONTENT_TYPE)
            .when().get(url+"/" + id);
        Assert.assertEquals(res.jsonPath().get("name"), "Nishant Rawat");
        Assert.assertEquals((Integer) res.jsonPath().get("tech_type_id"), 4);
        Assert.assertEquals(res.jsonPath().get("documentation_link"), "https://jsonformatter.org/");
        Assert.assertEquals(res.jsonPath().get("tech_description"), "description dataa");
        Assert.assertEquals(res.jsonPath().get("associated_tags[0]"), "Framework");
        Assert.assertEquals(res.jsonPath().get("associated_tags[1]"), "Angular");
        Assert.assertEquals(res.jsonPath().get("associated_tags[2]"), "Java");
        Assert.assertEquals((Integer) res.jsonPath().get("tech_logo"), 9);
    }

    @Test(dependsOnMethods = {"testPostRequest"}, priority = 3)
    void testDeleteRequest() {
        given().headers("Authorization", "Bearer " + token)
            .contentType(CONTENT_TYPE)
            .when().delete(url+"/" + id)
            .then().statusCode(200).log().all();
    }
}

