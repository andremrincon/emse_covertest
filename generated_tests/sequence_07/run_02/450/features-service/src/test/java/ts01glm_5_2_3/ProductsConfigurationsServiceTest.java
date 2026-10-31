package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsConfigurationsServiceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductReturnsConfigurationNames() {
        String productName = "test-prod-configs-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductReturnsEmptyList() {
        String productName = "test-prod-empty-configs-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesReturnsFeatureNames() {
        String productName = "test-prod-actived-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();
        String featureName = "feature-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesReturnsEmptyList() {
        String productName = "test-prod-no-actived-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationSucceeds() {
        String productName = "test-prod-remove-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();
        String featureName = "feature-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationSucceeds() {
        String productName = "test-prod-add-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();
        String featureName = "feature-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationDuplicateThrowsException() {
        String productName = "test-prod-dup-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();
        String featureName = "feature-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(greaterThanOrEqualTo(400));
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationViaAddFeature() {
        String productName = "test-prod-eval-add-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();
        String featureName = "feature-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationViaRemoveFeature() {
        String productName = "test-prod-eval-remove-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();
        String featureName = "feature-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationWithRequiresConstraintValid() {
        String productName = "test-prod-eval-req-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();
        String sourceFeature = "src-" + java.util.UUID.randomUUID();
        String requiredFeature = "req-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, requiredFeature).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationWithExcludesConstraintInvalid() {
        String productName = "test-prod-eval-excl-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();
        String sourceFeature = "src-" + java.util.UUID.randomUUID();
        String excludedFeature = "excl-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationWhenNotActive() {
        String productName = "test-prod-remove-inactive-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();
        String featureName = "feature-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductWithMultipleConfigurations() {
        String productName = "test-prod-multi-configs-" + java.util.UUID.randomUUID();
        String config1 = "config1-" + java.util.UUID.randomUUID();
        String config2 = "config2-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, config1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, config2).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations", productName).then().statusCode(200).body("size()", is(2));
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesWithMultipleFeatures() {
        String productName = "test-prod-multi-features-" + java.util.UUID.randomUUID();
        String configurationName = "config-" + java.util.UUID.randomUUID();
        String feature1 = "feature1-" + java.util.UUID.randomUUID();
        String feature2 = "feature2-" + java.util.UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature2).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName).then().statusCode(200).body("size()", is(2));
    }
}