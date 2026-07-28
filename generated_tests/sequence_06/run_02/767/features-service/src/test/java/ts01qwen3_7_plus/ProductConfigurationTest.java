package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithNoProductFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithSingleProductFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithMultipleProductFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        String featureName2 = "Feature2-" + UUID.randomUUID().toString();
        String featureName3 = "Feature3-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName1)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName2)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName3)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(200);
    }
}