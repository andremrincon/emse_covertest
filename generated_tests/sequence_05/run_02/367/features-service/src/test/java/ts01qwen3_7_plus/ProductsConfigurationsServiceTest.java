package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    private void createProduct(String productName) {
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
    }

    private void createFeature(String productName, String featureName) {
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
    }

    private void createConfiguration(String productName, String configurationName) {
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
    }

    private void addFeatureToConfiguration(String productName, String configurationName, String featureName) {
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String configName = "Conf-" + UUID.randomUUID().toString();
        createProduct(productName);
        createConfiguration(productName, configName);

        given()
            .when()
                .get("/products/{productName}/configurations", productName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNames() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();
        String configName = "Conf-" + UUID.randomUUID().toString();
        createProduct(productName);
        createFeature(productName, featureName);
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, featureName);

        given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureFromConfiguration() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();
        String configName = "Conf-" + UUID.randomUUID().toString();
        createProduct(productName);
        createFeature(productName, featureName);
        createConfiguration(productName, configName);

        given()
            .when()
                .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration() {
        String productName = "Prod-" + UUID.randomUUID().toString();
        String featureName = "Feat-" + UUID.randomUUID().toString();
        String configName = "Conf-" + UUID.randomUUID().toString();
        createProduct(productName);
        createFeature(productName, featureName);
        createConfiguration(productName, configName);
        addFeatureToConfiguration(productName, configName, featureName);

        given()
            .when()
                .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
                .statusCode(204);
    }
}