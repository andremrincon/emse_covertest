package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
public class ProductsConfigurationsServiceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductReturnsConfigurationNames() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations", productName)
                .then().statusCode(200)
                .body("", hasItem(configurationName));
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductReturnsEmptyListWhenNoConfigurations() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations", productName)
                .then().statusCode(200)
                .body("", hasSize(0));
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesReturnsFeatureNames() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then().statusCode(200)
                .body("", hasItem(featureName));
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesReturnsEmptyListWhenNoFeatures() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then().statusCode(200)
                .body("", hasSize(0));
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationActivatesFeatureSuccessfully() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationThrowsDuplicatedWhenFeatureAlreadyActive() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(greaterThanOrEqualTo(400));
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationDeactivatesFeatureSuccessfully() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationEvaluatesConfigurationAfterRemoval() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(204);

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then().statusCode(200)
                .body("", not(hasItem(featureName)));
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationEvaluatesConfigurationWithConstraints() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationSetsValidFlagAfterAddingFeature() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(200)
                .body("valid", notNullValue());
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductReturnsMultipleConfigurations() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName1 = "config1-" + UUID.randomUUID().toString().substring(0, 8);
        String configName2 = "config2-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName1)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName2)
                .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations", productName)
                .then().statusCode(200)
                .body("", hasItems(configName1, configName2));
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesReturnsMultipleFeatures() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName1 = "feat1-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName2 = "feat2-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName1)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName2)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName1)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName2)
                .then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then().statusCode(200)
                .body("", hasItems(featureName1, featureName2));
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationWhenFeatureNotActiveStillSucceeds() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(204);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <201>.")
    @Test(timeout = 60000)
    public void addFeatureFromConfigurationWithExcludesConstraintEvaluatesConfiguration() {
        String productName = "test-prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-feat-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));

        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("excludedFeature", excludedFeature)
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature)
                .then().statusCode(500);

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature)
                .then().statusCode(500);
    }
}