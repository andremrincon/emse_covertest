package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesCalledWhenGettingConfigurationWithProductFeatures_1() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Test feature description")
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesCalledWhenGettingConfigurationFeaturesEndpoint_2() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("description", "Test feature for available features")
                .when()
                .post("/products/" + productName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName + "/configurations/" + configurationName + "/features")
                .then()
                .statusCode(200)
                .body("$", hasItem(featureName));
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesCalledWhenProductHasNoFeatures_3() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/" + productName + "/configurations/" + configurationName)
                .then()
                .statusCode(200);
    }
}