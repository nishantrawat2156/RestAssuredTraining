package APIAssessment.Question2;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Scenario3 {

    int id;
   // String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOiIyMDIzLTAyLTEwVDE2OjUyOjExLjMzOVoiLCJ1c2VybmFtZSI6Im5pc2hhbnQucmF3YXRAM3BpbGxhcmdsb2JhbC5jb20ifQ.iZtPX8yv4kL0i-_m5D0e5FxlAS9An51OlnDGGrtSpOS-kiKPjrDOWNikVBqmGliOto1IW9w5hAQrBLx9Us3cIg";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOiIyMDIzLTAyLTE0VDE0OjU1OjE4LjI1OFoiLCJ1c2VybmFtZSI6Im5pc2hhbnQucmF3YXRAM3BpbGxhcmdsb2JhbC5jb20ifQ.Z0Pi9p2I1s_3DYPXG0e-H4RMebF7RQa1R6ScMkhgcdd6659NNiYwPc8Sr9koDUpJJ6yb2ern5kHcQHeamC2LWA";
    @Test
    void testPostRequest() {

        // Pass the correct data in all required fields in the body

        Pojo_Post data = new Pojo_Post();

        data.setName("Nishant Rawattt");
        data.setTech_type_id(4);
        data.setDescription("description data");
        data.setDoc_link("https://jsonformatter.org/");
        data.setLogo(9);
        String[] asso_tags = {"Framework", "Angular", "Java"};
        data.setAssoc_tags(asso_tags);

        Response res = given().headers("Authorization", "Bearer " + token)
            .contentType("application/json")
            .body(data)
            .when().post("https://stage-api-engage.3pillarglobal.com/api/technologies");
        id = res.jsonPath().getInt("id");
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = {"testPostRequest"})
    void testDeleteRequest() {
        given().headers("Authorization", "Bearer " + token)
            .contentType("application/json")
            .when().delete("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().statusCode(200).log().all();
    }
}
