package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    private String uniqueProduct() {
        return "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
    }

    private String uniqueConfig() {
        return "test-cfg-" + java.util.UUID.randomUUID().toString().substring(0, 8);
    }

    private String uniqueFeature() {
        return "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWhenProductHasFeatures() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();
        String featureName = uniqueFeature();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testActivedFeaturesWhenNoneActive() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testActiveFeatureInConfiguration() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();
        String featureName = uniqueFeature();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeactiveFeatureFromConfiguration() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();
        String featureName = uniqueFeature();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testHasActiveFeatureAfterActivation() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();
        String featureName = uniqueFeature();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then().statusCode(200).body(equalTo("[\"" + featureName + "\"]"));
    }

    @Test(timeout = 60000)
    public void testActivedFeaturesAfterDeactivation() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();
        String featureName = uniqueFeature();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then().statusCode(200).body(not(hasItem(featureName)));
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWithMultipleFeatures() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();
        String feature1 = uniqueFeature();
        String feature2 = uniqueFeature();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetValidOnConfigurationCreation() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductViaConfiguration() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testActiveMultipleFeaturesInConfiguration() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();
        String feature1 = uniqueFeature();
        String feature2 = uniqueFeature();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, feature1).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, feature2)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeactiveNonActiveFeature() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();
        String featureName = uniqueFeature();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCollectFeatureNamesWithEmptySet() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then().statusCode(200).body(equalTo("[]"));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsList() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testHasActiveFeatureAfterDeactivationCheck() {
        String productName = uniqueProduct();
        String configName = uniqueConfig();
        String featureName = uniqueFeature();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then().statusCode(200).body(not(hasItem(featureName)));
    }
}