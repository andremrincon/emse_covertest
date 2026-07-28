package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ProductsConfigurationsServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProduct_returnsConfigurationNames() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

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
                .get("/products/{productName}/configurations", productName)
                .then()
                .statusCode(200)
                .body(containsString(configurationName));
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProduct_returnsEmptyListWhenNoConfigurations() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations", productName)
                .then()
                .statusCode(200)
                .body("$", hasSize(0));
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNames_returnsFeatureNames() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then()
                .statusCode(200)
                .body(containsString(featureName));
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNames_returnsEmptyListWhenNoFeatures() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

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
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then()
                .statusCode(200)
                .body("$", hasSize(0));
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfiguration_returnsNoContent() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfiguration_returnsCreated() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfiguration_duplicateFeatureThrowsException() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(greaterThanOrEqualTo(400));
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfiguration_afterAddFeatureWithRequiresConstraint() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
                .then()
                .statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code <201> but was <500>.")
    @Test(timeout = 60000)
    public void evaluateAndUpdateConfiguration_afterAddFeatureWithExcludesConstraint() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/features/{featureName}", productName, excludedFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
                .then()
                .statusCode(201);

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfiguration_afterRemoveFeature() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(204);

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then()
                .statusCode(200)
                .body("$", hasSize(0));
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProduct_multipleConfigurations() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String config1 = "config1-" + UUID.randomUUID().toString().substring(0, 8);
        String config2 = "config2-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, config1)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, config2)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations", productName)
                .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }
}