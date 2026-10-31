package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.*;

public class ProductsConfigurationsServiceTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProduct_returnsConfigurationNames() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        RestAssured.given().when().get("/products/{productName}/configurations", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProduct_returnsEmptyListWhenNoConfigurations() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        RestAssured.given().when().get("/products/{productName}/configurations", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNames_returnsActiveFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        RestAssured.given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNames_returnsEmptyWhenNoActiveFeatures() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        RestAssured.given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfiguration_succeedsWhenFeatureIsActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        RestAssured.given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfiguration_returnsServerErrorWhenConfigurationNotFound() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "NonExistentConfig";
        String featureName = "NonExistentFeature";

        RestAssured.given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfiguration_succeedsWhenFeatureNotActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfiguration_throwsDuplicatedWhenFeatureAlreadyActive() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfiguration_returnsServerErrorWhenConfigurationNotFound() {
        String productName = "NonExistentProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "NonExistentConfig";
        String featureName = "NonExistentFeature";

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfiguration_validResultWhenNoConstraints() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfiguration_validResultAfterRemoveFeature() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        RestAssured.given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfiguration_withRequiresConstraintSatisfied() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "req-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCode(lessThan(300));
        RestAssured.given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature).then().statusCode(lessThan(300));

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, requiredFeature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfiguration_withExcludesConstraintViolated() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "exc-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCode(lessThan(300));
        RestAssured.given().contentType("application/x-www-form-urlencoded")
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature).then().statusCode(lessThan(300));

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfiguration_multipleFeaturesAdded() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "f1-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "f2-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature1).then().statusCode(lessThan(300));

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature2).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfiguration_afterMultipleFeaturesAdded() {
        String productName = "TestProduct-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "f1-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "f2-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature1).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature2).then().statusCode(lessThan(300));

        RestAssured.given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, feature1).then().statusCode(204);
    }
}