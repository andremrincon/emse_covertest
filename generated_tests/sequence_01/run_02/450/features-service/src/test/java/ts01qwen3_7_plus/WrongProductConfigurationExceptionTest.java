package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testWrongProductConfigurationException() {
        String productName = "Product_" + UUID.randomUUID().toString();
        String featureA = "FeatureA";
        String featureB = "FeatureB";
        String configName = "Config_" + UUID.randomUUID().toString();

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCode(lessThan(300));

        given()
                .formParam("sourceFeature", featureA)
                .formParam("requiredFeature", featureB)
                .when()
                .post("/products/" + productName + "/constraints/requires")
                .then()
                .statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureA).then().statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
                .then()
                .statusCode(200);
    }
}