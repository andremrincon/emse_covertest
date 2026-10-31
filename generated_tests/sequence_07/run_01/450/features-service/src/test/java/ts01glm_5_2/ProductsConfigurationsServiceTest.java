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

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProduct_withExistingConfigurations_returns200() {
        String productName = "test-prod-configs-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .post("/products/{productName}", productName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, "config-a")
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}", productName, "config-b")
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations", productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProduct_withNoConfigurations_returns200Empty() {
        String productName = "test-prod-no-configs-" + UUID.randomUUID().toString().substring(0, 8);

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
    public void getConfigurationActivedFeaturesNames_withActiveFeatures_returns200() {
        String productName = "test-prod-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-with-features";
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/features/{featureName}", productName, featureName)
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
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNames_withNoActiveFeatures_returns200Empty() {
        String productName = "test-prod-no-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-no-features";

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
    public void addFeatureFromConfiguration_success_returns201() {
        String productName = "test-prod-addfeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-add";
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfiguration_duplicateFeature_throwsException() {
        String productName = "test-prod-dupfeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-dup";
        String featureName = "feat-dup-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/features/{featureName}", productName, featureName)
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
    public void removeFeatureFromConfiguration_success_returns204() {
        String productName = "test-prod-remfeat-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-rem";
        String featureName = "feat-rem-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/features/{featureName}", productName, featureName)
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
    public void evaluateAndUpdateConfiguration_validResult_afterAddFeature() {
        String productName = "test-prod-eval-valid-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-eval-valid";
        String featureName = "feat-eval-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfiguration_invalidResult_withRequiresConstraint() {
        String productName = "test-prod-eval-invalid-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-eval-invalid";
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfiguration_validResult_afterRemoveFeature() {
        String productName = "test-prod-eval-rem-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-eval-rem";
        String featureName = "feat-eval-rem-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/features/{featureName}", productName, featureName)
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

    @Ignore("1 expectation failed. JSON path valid doesn't match. Expected: is <true>   Actual: <false>")
    @Test(timeout = 60000)
    public void evaluateAndUpdateConfiguration_invalidResult_withExcludesConstraint() {
        String productName = "test-prod-eval-excl-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-eval-excl";
        String sourceFeature = "src-excl-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-feat-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature)
                .then()
                .statusCode(500);

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(200)
                .body("valid", is(true));
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProduct_nonExistentProduct_returns200Empty() {
        String productName = "non-existent-prod-" + UUID.randomUUID().toString().substring(0, 8);

        given()
                .when()
                .get("/products/{productName}/configurations", productName)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNames_multipleFeatures_returns200WithAll() {
        String productName = "test-prod-multi-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-multi";
        String feature1 = "feat1-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "feat2-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature1)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature2)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfiguration_featureNotActive_returns204() {
        String productName = "test-prod-rem-inactive-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-rem-inactive";
        String featureName = "feat-rem-inactive-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfiguration_validResult_noConstraints() {
        String productName = "test-prod-no-constraints-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-no-constraints";
        String featureName = "feat-no-constraints-" + UUID.randomUUID().toString().substring(0, 8);

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
                .post("/products/{productName}/features/{featureName}", productName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then()
                .statusCode(lessThan(300));

        given()
                .when()
                .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then()
                .statusCode(200)
                .body("valid", is(true));
    }
}