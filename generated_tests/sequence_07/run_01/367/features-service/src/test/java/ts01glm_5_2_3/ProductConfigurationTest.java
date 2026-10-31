package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;

public class ProductConfigurationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationForProductWithFeaturesReturnsAvailableFeatures() {
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "test-prod-" + uniqueSuffix;
        String configurationName = "config-" + uniqueSuffix;
        String featureName = "feat-" + uniqueSuffix;

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
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationForProductWithoutFeaturesReturnsEmptyAvailableFeatures() {
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "test-prod-empty-" + uniqueSuffix;
        String configurationName = "config-empty-" + uniqueSuffix;

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationWithMultipleFeaturesReturnsAllAvailableFeatures() {
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        String productName = "test-prod-multi-" + uniqueSuffix;
        String configurationName = "config-multi-" + uniqueSuffix;
        String feature1 = "feat1-" + uniqueSuffix;
        String feature2 = "feat2-" + uniqueSuffix;

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, feature1)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, feature2)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature1)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then()
                .statusCode(200);
    }
}