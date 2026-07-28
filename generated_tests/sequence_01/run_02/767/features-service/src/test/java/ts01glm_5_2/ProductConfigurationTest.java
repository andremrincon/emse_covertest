package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void availableFeaturesIsCalledWhenAddingFeatureToConfiguration() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "test-prod-" + uuid;
        String featureName = "test-feature-" + uuid;
        String configurationName = "test-config-" + uuid;

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void availableFeaturesIsCalledWhenGettingConfigurationFeatures() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "test-prod-get-" + uuid;
        String featureName = "test-feature-get-" + uuid;
        String configurationName = "test-config-get-" + uuid;

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                        productName, configurationName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features",
                        productName, configurationName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void availableFeaturesIsCalledWhenGettingConfigurationDetails() {
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String productName = "test-prod-detail-" + uuid;
        String featureName = "test-feature-detail-" + uuid;
        String configurationName = "test-config-detail-" + uuid;

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}",
                        productName, configurationName)
                .then()
                .statusCode(200);
    }
}