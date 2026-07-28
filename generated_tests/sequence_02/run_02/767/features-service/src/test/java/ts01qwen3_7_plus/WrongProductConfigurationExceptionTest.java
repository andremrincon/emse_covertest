package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    private String baseUrl;
    private String productName;
    private String featureName1;
    private String featureName2;
    private String configurationName;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;

        String uuid = UUID.randomUUID().toString();
        productName = "Product-" + uuid;
        featureName1 = "Feature1-" + uuid;
        featureName2 = "Feature2-" + uuid;
        configurationName = "Config-" + uuid;
    }

    @Test(timeout = 60000)
    public void testWrongProductConfigurationExceptionOnAddFeature() {
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName2).then().statusCode(lessThan(300));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", featureName1)
            .formParam("excludedFeature", featureName2)
            .when().post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName1).then().statusCode(lessThan(300));

        given()
            .when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName2)
            .then().statusCode(500);
    }
}