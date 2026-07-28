package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductReturns200WhenProductHasConfigurations() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductReturns200WhenNoConfigurationsExist() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesReturns200WhenFeaturesExist() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesReturns200WhenNoFeaturesActivated() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationReturns204WhenFeatureExists() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationReturns204WhenFeatureNotActivated() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationReturns201WhenFeatureNotAlreadyActive() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationReturns500WhenFeatureAlreadyActive() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationValidWhenAddingFeatureWithoutConstraints() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationAfterRemovingFeature() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationWithRequiresConstraintSatisfied() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "feat-src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "feat-req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
                .when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationWithExcludesConstraintSatisfied() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "test-config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "feat-src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "feat-exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature)
                .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationReturns500WhenConfigurationDoesNotExist() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "nonexistent-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationReturns500WhenConfigurationDoesNotExist() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "nonexistent-config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductReturns500WhenProductDoesNotExist() {
        String productName = "nonexistent-prod-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().get("/products/" + productName + "/configurations").then().statusCode(200);
    }
}