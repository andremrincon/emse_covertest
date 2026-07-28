package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;

public class FeatureTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/features")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Updated description text")
            .when()
                .put("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeatureTriggersEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(anyOf(equalTo(201), equalTo(500)));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationTriggersEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesCoversGetProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureTriggersEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationTriggersEquals() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
                .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureNameToDifferentProducts() {
        String productName1 = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String productName2 = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "SharedFeature-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName2).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName1 + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName2 + "/features/" + featureName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductCoversGetProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithDifferentFeatureNames() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "FeatA-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "FeatB-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));

        given()
            .formParam("sourceFeature", feature1)
            .formParam("requiredFeature", feature2)
            .when()
                .post("/products/" + productName + "/constraints/requires")
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationCoversGetProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Config-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        given()
            .when()
                .get("/products/" + productName + "/configurations/" + configName)
            .then()
                .statusCode(200);
    }
}