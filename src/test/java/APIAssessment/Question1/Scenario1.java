package APIAssessment.Question1;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Files.EngageData.*;
import static io.restassured.RestAssured.given;

public class Scenario1 {

    @Test
    void testGetData() {

        given().headers("Authorization", "Bearer " + token).contentType(CONTENT_TYPE)
            .when().get(url)
            .then().statusCode(200).log().all();
    }

    @Test
    void testGetParticularIdData() {
        Response res = given().headers("Authorization", "Bearer " + token).contentType(CONTENT_TYPE)
            .when().get(url +"/1648");
        System.out.println(res.getStatusCode());
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().get("name"), "Akamai Identity Cloud");
        Assert.assertEquals((Integer) res.jsonPath().get("tech_type_id"), 3);
    }
}
