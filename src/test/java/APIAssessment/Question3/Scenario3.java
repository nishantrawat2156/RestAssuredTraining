package APIAssessment.Question3;

import APIAssessment.Question2.Pojo_Post;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Scenario3 {

    //Pass the Existing data (duplicate data) into the body
    int id;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOiIyMDIzLTAyLTEwVDE2OjUyOjExLjMzOVoiLCJ1c2VybmFtZSI6Im5pc2hhbnQucmF3YXRAM3BpbGxhcmdsb2JhbC5jb20ifQ.iZtPX8yv4kL0i-_m5D0e5FxlAS9An51OlnDGGrtSpOS-kiKPjrDOWNikVBqmGliOto1IW9w5hAQrBLx9Us3cIg";

    @Test
    void testPostRequest() {

        Pojo_Post data1 = new Pojo_Post();

        data1.setName("Nishant Rawat");
        data1.setTech_type_id(4);
        data1.setDescription("description data");
        data1.setDoc_link("https://jsonformatter.org/");
        data1.setLogo(9);
        String[] asso_tags = {"Framework", "Angular", "Java"};
        data1.setAssoc_tags(asso_tags);

        id = given().headers("Authorization", "Bearer " + token)
            .contentType("application/json")
            .body(data1)
            .when().post("https://stage-api-engage.3pillarglobal.com/api/technologies").jsonPath().getInt("id");
        System.out.println(id);
    }

    @Test(dependsOnMethods = {"testPostRequest"}, priority = 2)
    void testPutRequest() {

        Pojo_Post data2 = new Pojo_Post();

        data2.setName("Nishant Rawat");
        data2.setTech_type_id(4);
        data2.setDescription("description data");
        data2.setDoc_link("https://jsonformatter.org/");
        data2.setLogo(9);
        String[] asso_tags = {"Framework", "Angular", "Java"};
        data2.setAssoc_tags(asso_tags);

        given().headers("Authorization", "Bearer " + token)
            .contentType("application/json")
            .body(data2)
            .when().put("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().statusCode(409).log().all();
    }

    @Test(dependsOnMethods = {"testPostRequest"}, priority = 3)
    void testDeleteRequest() {
        given().headers("Authorization", "Bearer " + token)
            .contentType("application/json")
            .when().delete("https://stage-api-engage.3pillarglobal.com/api/technologies/" + id)
            .then().statusCode(200).log().all();
    }
}


