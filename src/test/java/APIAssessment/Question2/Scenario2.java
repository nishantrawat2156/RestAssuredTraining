package APIAssessment.Question2;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Scenario2 {

    // Pass the data in the body without “tech_type_id”

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOiIyMDIzLTAyLTEwVDE2OjUyOjExLjMzOVoiLCJ1c2VybmFtZSI6Im5pc2hhbnQucmF3YXRAM3BpbGxhcmdsb2JhbC5jb20ifQ.iZtPX8yv4kL0i-_m5D0e5FxlAS9An51OlnDGGrtSpOS-kiKPjrDOWNikVBqmGliOto1IW9w5hAQrBLx9Us3cIg";

    @Test
    void testPostRequest() {

        Pojo_Post data = new Pojo_Post();

        data.setName("Nishant");
        data.setDescription("description data");
        data.setDoc_link("https://jsonformatter.org/");
        data.setLogo(9);
        String[] asso_tags = {"Framework", "Angular", "Java"};
        data.setAssoc_tags(asso_tags);

        given().headers("Authorization", "Bearer " + token)
            .contentType("application/json")
            .body(data)
            .when().post("https://stage-api-engage.3pillarglobal.com/api/technologies")
            .then().statusCode(400).log().all();
    }
}
