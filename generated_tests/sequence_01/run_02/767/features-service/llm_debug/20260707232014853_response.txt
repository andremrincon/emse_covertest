package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductExercisesSetNameAndSetProduct() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "A test feature description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesExercisesGetProductAndEquals() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Feature description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        when()
            .get("/products/{productName}/features", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureExercisesSetName() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Original description")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Updated description")
        .when()
            .put("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationExercisesEquals() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Feature for config")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesExercisesEquals() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Feature for config")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationExercisesEquals() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Feature for config")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureToConfigurationTwiceExercisesEqualsSameObject() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Feature for config")
        .when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToConfigurationExercisesEqualsDifferentNames() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName1 = "FeatureA-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName2 = "FeatureB-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName1)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName2)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName1)
            .then().statusCode(lessThan(300));

        when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName2)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProductExercisesGetProduct() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        when()
            .delete("/products/{productName}/features/{featureName}", productName, featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductExercisesFeatureEqualsAndGetProduct() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        when()
            .get("/products/{productName}", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProductExercisesFeatureEquals() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        when()
            .get("/products/{productName}/configurations", productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationWithNameExercisesFeatureEquals() {
        String productName = "TestProduct-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "TestConfig-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeature-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/{productName}", productName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then().statusCode(lessThan(300));

        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then().statusCode(lessThan(300));

        when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
        .then()
            .statusCode(200);
    }
}