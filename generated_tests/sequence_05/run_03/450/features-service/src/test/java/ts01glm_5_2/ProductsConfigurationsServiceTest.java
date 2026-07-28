package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProductReturns200() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProductWithNoConfigurationsReturns200() {
        String productName = "EmptyProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNamesReturns200() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNamesWithNoActiveFeaturesReturns200() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfigurationReturns204() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfigurationWhenFeatureNotActiveReturns204() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureFromConfigurationReturns201() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureFromConfigurationWhenAlreadyActiveReturns500() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testEvaluateAndUpdateConfigurationAfterAddFeature() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateAndUpdateConfigurationAfterRemoveFeature() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testEvaluateAndUpdateConfigurationWithRequiresConstraint() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "source-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "required-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{sourceFeature}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{requiredFeature}", productName, requiredFeature).then().statusCode(lessThan(300));
        given().queryParam("source", sourceFeature).queryParam("required", requiredFeature).when().post("/products/{productName}/constraints/requires", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{sourceFeature}", productName, configurationName, sourceFeature).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{requiredFeature}", productName, configurationName, requiredFeature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testEvaluateAndUpdateConfigurationWithExcludesConstraint() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "source-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "excluded-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{sourceFeature}", productName, sourceFeature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{excludedFeature}", productName, excludedFeature).then().statusCode(lessThan(300));
        given().queryParam("source", sourceFeature).queryParam("excluded", excludedFeature).when().post("/products/{productName}/constraints/excludes", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{sourceFeature}", productName, configurationName, sourceFeature).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{excludedFeature}", productName, configurationName, excludedFeature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNamesAfterRemoveReturns200() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "feature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProductWithMultipleConfigurationsReturns200() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName1 = "config1-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName2 = "config2-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName2).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations", productName).then().statusCode(200).body("size()", is(2));
    }
}