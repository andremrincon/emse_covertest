package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FeatureTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateProductAndAddFeature() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "A test feature")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/features", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "test-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Updated description")
        .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddSecondFeatureToProduct() {
        String productName = "test-product-" + UUID.randomUUID();
        String feature1 = "feature-a-" + UUID.randomUUID();
        String feature2 = "feature-b-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/{productName}/features/{featureName}", productName, feature2)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeature() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "dup-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "config-feature-" + UUID.randomUUID();
        String configName = "test-config-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "del-config-feature-" + UUID.randomUUID();
        String configName = "del-config-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "get-config-feature-" + UUID.randomUUID();
        String configName = "get-config-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeatureToConfiguration() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "dup-config-feature-" + UUID.randomUUID();
        String configName = "dup-config-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProduct() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "del-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductWithFeatures() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "get-prod-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetAllProducts() {
        String productName = "test-product-" + UUID.randomUUID();
        String featureName = "all-prods-feature-" + UUID.randomUUID();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products")
        .then()
            .statusCode(200);
    }
}