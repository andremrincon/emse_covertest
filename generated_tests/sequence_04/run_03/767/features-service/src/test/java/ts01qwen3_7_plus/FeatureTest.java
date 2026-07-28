package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCreateFeatureExercisesSetNameAndSetProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureExercisesSetName() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "Updated description")
            .when()
            .put("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesExercisesGetProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationExercisesEquals() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesExercisesEquals() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateSameFeatureNameForDifferentProductsExercisesEquals() {
        String productName1 = "Product1-" + UUID.randomUUID().toString();
        String productName2 = "Product2-" + UUID.randomUUID().toString();
        String featureName = "SharedFeature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName1)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName2)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName1 + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName2 + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureExercisesSetProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfigurationExercisesEquals() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductWithFeaturesExercisesGetProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsExercisesEquals() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureWithDescriptionExercisesSetName() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .formParam("description", "New description for feature")
            .when()
            .put("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToConfigurationExercisesEquals() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        String featureName2 = "Feature2-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/" + productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName1)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/features/" + featureName2)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName1)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName2)
            .then()
            .statusCode(201);
    }
}