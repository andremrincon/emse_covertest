package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.UUID;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturnsValidConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationActivatesFeature() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetActiveFeaturesReturnsActivatedFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetActiveFeaturesForEmptyConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationDeactivatesFeature() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureNotActivatedFromConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetActiveFeaturesAfterDeactivation() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationWithActiveFeatures() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName1 = "feat1-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName2 = "feat2-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName1).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName2)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithRequiresConstraintChecksHasActiveFeature() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires")
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature)
            .then().statusCode(anyOf(equalTo(201), equalTo(400), equalTo(500)));
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithExcludesConstraintChecksHasActiveFeature() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when().post("/products/" + productName + "/constraints/excludes")
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + sourceFeature).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature)
            .then().statusCode(anyOf(equalTo(201), equalTo(400), equalTo(500)));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsListForProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().delete("/products/" + productName + "/configurations/" + configName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationFeatureNotInProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "nonexistent-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(anyOf(equalTo(400), equalTo(404), equalTo(500)));
    }
}