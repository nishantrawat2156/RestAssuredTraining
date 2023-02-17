package APIAssessment.Question4;

import APIAssessment.Question2.Pojo_Post;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Scenario1and2 {

    //Pass the id in the DELETE request
    int id;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOiIyMDIzLTAyLTEwVDE2OjUyOjExLjMzOVoiLCJ1c2VybmFtZSI6Im5pc2hhbnQucmF3YXRAM3BpbGxhcmdsb2JhbC5jb20ifQ.iZtPX8yv4kL0i-_m5D0e5FxlAS9An51OlnDGGrtSpOS-kiKPjrDOWNikVBqmGliOto1IW9w5hAQrBLx9Us3cIg";
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
            .contentType("application/json")
            .body(data)
            .when().post("https://stage-api-engage.3pillarglobal.com/api/technologies").jsonPath().getInt("id");
        System.out.println(id);
    }

    @Test(dependsOnMethods = {"testPostRequest"}, priority = 2)
    void testDeleteRequest() {
        given().headers("Authorization", "Bearer " + token)
            .contentType("application/json")
            .when().delete("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().statusCode(200).log().all();
    }

    @Test(dependsOnMethods = {"testDeleteRequest"}, priority = 3)
    void testGetData() {
        Response res = given().headers("Authorization", "Bearer " + token).contentType("application/json")
            .when().get("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id);
        System.out.println(res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 404);
    }
}
