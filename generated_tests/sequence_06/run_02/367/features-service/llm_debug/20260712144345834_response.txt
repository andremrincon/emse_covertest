package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    private String createProduct() {
        String productName = "prod-" + UUID.randomUUID().toString().substring(0, 8);
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        return productName;
    }

    private String createFeature(String productName) {
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);
        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        return featureName;
    }

    private String createConfiguration(String productName) {
        String configName = "config-" + UUID.randomUUID().toString().substring(0, 8);
        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));
        return configName;
    }

    @Test(timeout = 60000)
    public void testAddFeatureSetsNameAndProduct() {
        String productName = createProduct();
        String featureName = "feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesRetrievesProduct() {
        String productName = createProduct();
        createFeature(productName);

        given().when()
            .get("/products/{productName}/features", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureSetsName() {
        String productName = createProduct();
        String featureName = createFeature(productName);

        given().when()
            .formParam("description", "Updated description text")
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationTriggersEquals() {
        String productName = createProduct();
        String featureName = createFeature(productName);
        String configName = createConfiguration(productName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeatureTriggersEqualsSameObject() {
        String productName = createProduct();
        String featureName = createFeature(productName);

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesTriggersEqualsAndProduct() {
        String productName = createProduct();
        String featureName = createFeature(productName);
        String configName = createConfiguration(productName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTwoFeaturesSameNameDifferentProductsTriggersEquals() {
        String product1 = createProduct();
        String product2 = createProduct();
        String featureName = "shared-feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when()
            .post("/products/{productName}/features/{featureName}", product1, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", product2, featureName)
            .then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProductTriggersEquals() {
        String productName = createProduct();
        String featureName = createFeature(productName);

        given().when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationTriggersEquals() {
        String productName = createProduct();
        String featureName = createFeature(productName);
        String configName = createConfiguration(productName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductWithMultipleFeaturesTriggersEquals() {
        String productName = createProduct();
        createFeature(productName);
        createFeature(productName);

        given().when()
            .get("/products/{productName}", productName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToConfigurationTriggersEquals() {
        String productName = createProduct();
        String feature1 = createFeature(productName);
        String feature2 = createFeature(productName);
        String configName = createConfiguration(productName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, feature1)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, feature2)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetAllConfigurationsWithFeaturesTriggersEquals() {
        String productName = createProduct();
        String featureName = createFeature(productName);
        String configName = createConfiguration(productName);

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .get("/products/{productName}/configurations", productName)
            .then().statusCode(200);
    }
}