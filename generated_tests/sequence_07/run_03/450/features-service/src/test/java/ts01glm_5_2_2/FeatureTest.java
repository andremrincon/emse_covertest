package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCreateFeatureSetsNameAndProduct() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureSetsName() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void testGetFeaturesReturnsProduct() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/features", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationTriggersEquals() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "test-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void testGetConfigurationFeaturesTriggersEqualsAndGetProduct() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "test-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void testDeleteFeatureFromConfigurationTriggersEquals() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "test-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

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
    public void testAddMultipleFeaturesTriggersEqualsComparison() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "test-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "feat1-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "feat2-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, feature2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, feature1).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, feature2)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductTriggersGetProductOnFeature() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureTriggersEquals() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsTriggersEquals() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "test-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationTriggersEqualsAndGetProduct() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configName = "test-config-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescriptionSetsNameAndProduct() {
        String productName = "test-prod-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feat-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "A test feature description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }
}