package APIAssessment.Question2;

import org.testng.annotations.Test;

import static Files.EngageData.*;
import static io.restassured.RestAssured.given;

public class Scenario2 {

    // Pass the data in the body without “tech_type_id”

    @Test
    void testPostRequest() {

        Pojo_Post data = new Pojo_Post();

        data.setName("Nishant");
        data.setDescription("description data");
        data.setDoc_link("https://jsonformatter.org/");
        data.setLogo(9);
        data.setAssoc_tags(asso_tags);

        given().headers("Authorization", "Bearer " + token)
            .contentType(CONTENT_TYPE)
            .body(data)
            .when().post(url)
            .then().statusCode(400).log().all();
    }
}
