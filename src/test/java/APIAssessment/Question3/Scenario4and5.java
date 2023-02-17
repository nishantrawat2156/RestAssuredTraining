package APIAssessment.Question3;

import APIAssessment.Question2.Pojo_Post;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Scenario4and5 {
    //Update Name, and Description from existing data in the body

    int id;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOiIyMDIzLTAyLTEwVDE2OjUyOjExLjMzOVoiLCJ1c2VybmFtZSI6Im5pc2hhbnQucmF3YXRAM3BpbGxhcmdsb2JhbC5jb20ifQ.iZtPX8yv4kL0i-_m5D0e5FxlAS9An51OlnDGGrtSpOS-kiKPjrDOWNikVBqmGliOto1IW9w5hAQrBLx9Us3cIg";
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
            .contentType("application/json")
            .body(data)
            .when().post("https://stage-api-engage.3pillarglobal.com/api/technologies").jsonPath().getInt("id");
        System.out.println(id);
    }

    @Test(dependsOnMethods = {"testPostRequest"}, priority = 2)
    void testPutRequest() {
        data.setName("ExampleAA");
        data.setTech_type_id(4);
        data.setDoc_link("https://jsonformatter.org/");
        data.setDescription("description data valuee");
        String[] asso_tags = {"Framework", "Angular", "Java"};
        data.setAssoc_tags(asso_tags);
        data.setLogo(9);

        given().headers("Authorization", "Bearer " + token)
            .contentType("application/json")
            .body(data)
            .when().put("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().statusCode(200).log().all();
    }

    @Test(dependsOnMethods = {"testPutRequest"}, priority = 3)
    void testGetData() {
        Response res = given().headers("Authorization", "Bearer " + token).contentType("application/json")
            .when().get("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id);
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().get("name"), "ExampleAA");
        Assert.assertEquals(res.jsonPath().get("tech_description"), "description data valuee");
    }

    @Test(dependsOnMethods = {"testPostRequest"}, priority = 4)
    void testDeleteRequest() {
        given().headers("Authorization", "Bearer " + token)
            .contentType("application/json")
            .when().delete("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().statusCode(200).log().all();
    }
}
