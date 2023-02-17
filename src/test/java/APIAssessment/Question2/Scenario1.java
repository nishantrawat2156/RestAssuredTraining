package APIAssessment.Question2;

import org.testng.annotations.Test;

import static Files.EngageData.*;
import static io.restassured.RestAssured.given;

public class Scenario1 {

    @Test
    void testPostRequest() {

        //Pass the data in the body without “name” in the body

        Pojo_Post data = new Pojo_Post();

        data.setTech_type_id(4);
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
