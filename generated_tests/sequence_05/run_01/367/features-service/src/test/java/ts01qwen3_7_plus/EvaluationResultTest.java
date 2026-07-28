package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;

public class EvaluationResultTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testEvaluationResultInstantiationOnAddFeatureToConfiguration() {
        String productName = "Prod" + UUID.randomUUID().toString().replace("-", "");
        String featureName = "Feat" + UUID.randomUUID().toString().replace("-", "");
        String configName = "Conf" + UUID.randomUUID().toString().replace("-", "");

        RestAssured.given().when().post("/products/" + productName).then().statusCode(org.hamcrest.Matchers.lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(org.hamcrest.Matchers.lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(org.hamcrest.Matchers.lessThan(300));

        RestAssured.given()
                .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }
}