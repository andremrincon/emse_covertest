package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import java.util.UUID;

public class FeatureTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    private String createProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));
        return productName;
    }

    private void createFeature(String productName, String featureName) {
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
    }

    private void createConfiguration(String productName, String configName) {
        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));
    }

    private void addFeatureToConfiguration(String productName, String configName, String featureName) {
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, featureName)
            .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductCoversSetProductAndSetName() {
        String productName = createProduct();
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescriptionCoversSetProductAndSetName() {
        String productName = createProduct();
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when()
            .formParam("description", "Test description for feature")
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescriptionCoversSetName() {
        String productName = createProduct();
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        createFeature(productName, featureName);

        given().when()
            .formParam("description", "Updated description")
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesCoversGetProduct() {
        String productName = createProduct();
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        createFeature(productName, featureName);

        given().when()
            .get("/products/{productName}/features", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductCoversGetProductAndEquals() {
        String productName = createProduct();
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        createFeature(productName, featureName);

        given().when()
            .get("/products/{productName}", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationCoversEquals() {
        String productName = createProduct();
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        createFeature(productName, featureName);
        createConfiguration(productName, configName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureToConfigurationTwiceCoversEqualsTrue() {
        String productName = createProduct();
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        createFeature(productName, featureName);
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, featureName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToConfigurationCoversEqualsNameMismatch() {
        String productName = createProduct();
        String feature1 = "FeatA-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "FeatB-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        createFeature(productName, feature1);
        createFeature(productName, feature2);
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, feature1);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, feature2)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesCoversEqualsInSet() {
        String productName = createProduct();
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        createFeature(productName, featureName);
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, featureName);

        given().when()
            .get("/products/{productName}/configurations/{configurationName}/features",
                productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationCoversEqualsInRemoval() {
        String productName = createProduct();
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        createFeature(productName, featureName);
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, featureName);

        given().when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProductCoversEquals() {
        String productName = createProduct();
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        createFeature(productName, featureName);

        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsCoversGetProduct() {
        String productName = createProduct();
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        createFeature(productName, featureName);
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, featureName);

        given().when()
            .get("/products/{productName}/configurations", productName)
            .then().statusCode(200);
    }
}