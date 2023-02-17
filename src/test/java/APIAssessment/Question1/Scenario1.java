package APIAssessment.Question1;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Scenario1 {

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOiIyMDIzLTAyLTEwVDE2OjUyOjExLjMzOVoiLCJ1c2VybmFtZSI6Im5pc2hhbnQucmF3YXRAM3BpbGxhcmdsb2JhbC5jb20ifQ.iZtPX8yv4kL0i-_m5D0e5FxlAS9An51OlnDGGrtSpOS-kiKPjrDOWNikVBqmGliOto1IW9w5hAQrBLx9Us3cIg";

    @Test
    void testGetData() {

        given().headers("Authorization", "Bearer " + token).contentType("application/json")
            .when().get("https://stage-api-engage.3pillarglobal.com/api/technologies")
            .then().statusCode(200).log().all();
    }

    @Test
    void testGetParticularIdData() {
        Response res = given().headers("Authorization", "Bearer " + token).contentType("application/json")
            .when().get("https://stage-api-engage.3pillarglobal.com/api/technologies/1648");
        System.out.println(res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().get("name"), "Akamai Identity Cloud");
        Assert.assertEquals((Integer) res.jsonPath().get("tech_type_id"), 3);
    }
}
