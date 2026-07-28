package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    private String productName;
    private String featureName;
    private String configurationName;

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        productName = "Product-" + UUID.randomUUID().toString();
        featureName = "Feature-" + UUID.randomUUID().toString();
        configurationName = "Configuration-" + UUID.randomUUID().toString();
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesTriggeredOnAddFeatureToConfiguration() {
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesTriggeredOnGetConfiguration() {
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(200);
    }
}