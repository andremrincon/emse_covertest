package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testCreateFeatureExercisesSetNameAndSetProduct() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "A test feature description")
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureExercisesSetName() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "backlit-keyboard";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "RGB backlit keyboard")
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "RGB backlit keyboard with customizable zones and per-key lighting.")
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesExercisesGetProductAndEquals() {
        String productName = "AeroBook-Pro-15-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName1 = "15-inch-OLED";
        String featureName2 = "16GB-RAM";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName2).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/features", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationExercisesEqualsSameFeatureComparison() {
        String productName = "QuantumLeap-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "standard-gpu-cluster";
        String featureName = "distributed-training";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeatureToConfigurationExercisesEqualsSameObject() {
        String productName = "DupTest-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "base-config";
        String featureName = "sso";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesExercisesEqualsWithDifferentNames() {
        String productName = "Laptop-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "us-standard-16gb";
        String featureName1 = "fingerprint-scanner";
        String featureName2 = "4k-display";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName2).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationExercisesEqualsAndHashCode() {
        String productName = "DelConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "Premium-Tier";
        String featureName = "Realtime-Analytics-Dashboard";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureExercisesGetProductInRetrieval() {
        String productName = "DelFeat-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "heart-rate-monitor";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/features", productName)
            .then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescriptionExercisesSetProductAndEquals() {
        String productName = "DescTest-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Blood-Oxygen-Sensor";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.")
            .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductExercisesGetProductMethod() {
        String productName = "GetProd-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "feature-a").then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, "feature-b").then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToConfigurationExercisesEqualsDifferentProducts() {
        String productName1 = "Multi1-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String productName2 = "Multi2-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "base";
        String featureName1 = "feat-a";
        String featureName2 = "feat-b";

        given().when().post("/products/{productName}", productName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}", productName2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName1, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName1, featureName2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName1, configurationName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName1, configurationName, featureName1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName1, configurationName, featureName2).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName1, configurationName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureWithDifferentDescriptionExercisesSetNameAndProduct() {
        String productName = "UpdateTest-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "stylus-support";

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Updated stylus support with pressure sensitivity")
            .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
            .then()
            .statusCode(200);
    }
}