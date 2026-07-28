package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setUpClass() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateFeature() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName = "Feat-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName = "Feat-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/features", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName = "Feat-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .contentType(ContentType.URLENC)
            .formParam("description", "Updated description")
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName = "Feat-" + java.util.UUID.randomUUID();
        String configName = "Config-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureToConfigurationTwice() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName = "Feat-" + java.util.UUID.randomUUID();
        String configName = "Config-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddDifferentFeaturesToConfiguration() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName1 = "Feat1-" + java.util.UUID.randomUUID();
        String featureName2 = "Feat2-" + java.util.UUID.randomUUID();
        String configName = "Config-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName1)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName2)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName1)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName2)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName = "Feat-" + java.util.UUID.randomUUID();
        String configName = "Config-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName = "Feat-" + java.util.UUID.randomUUID();
        String configName = "Config-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProduct() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName = "Feat-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductByNameWithFeature() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName = "Feat-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationByNameWithFeature() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName = "Feat-" + java.util.UUID.randomUUID();
        String configName = "Config-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProductWithFeature() {
        String productName = "Prod-" + java.util.UUID.randomUUID();
        String featureName = "Feat-" + java.util.UUID.randomUUID();
        String configName = "Config-" + java.util.UUID.randomUUID();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations", productName)
            .then()
            .statusCode(200);
    }
}