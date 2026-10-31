package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateFeature_CoversSetNameAndSetProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeatures_CoversGetProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature_CoversSetName() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Original description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Updated description")
        .when()
            .put("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_CoversEqualsFirstBranch() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        String configName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeatureToConfiguration_CoversEqualsDuplicateCheck() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        String configName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures_CoversEqualsInCollection() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature 1")
        .when()
            .post("/products/" + productName + "/features/" + featureName1)
        .then().statusCode(lessThan(300));

        String featureName2 = "Feature2-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature 2")
        .when()
            .post("/products/" + productName + "/features/" + featureName2)
        .then().statusCode(lessThan(300));

        String configName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName1)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName2)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_CoversEqualsInRemoval() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        String configName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_CoversEqualsInProductFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .delete("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductWithFeatures_CoversGetProductAndEquals() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName)
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddMultipleFeaturesToConfiguration_CoversEqualsMultipleComparisons() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature 1")
        .when()
            .post("/products/" + productName + "/features/" + featureName1)
        .then().statusCode(lessThan(300));

        String featureName2 = "Feature2-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature 2")
        .when()
            .post("/products/" + productName + "/features/" + featureName2)
        .then().statusCode(lessThan(300));

        String featureName3 = "Feature3-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature 3")
        .when()
            .post("/products/" + productName + "/features/" + featureName3)
        .then().statusCode(lessThan(300));

        String configName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName1)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName2)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName3)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription_CoversSetNameWithExistingFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Initial description")
        .when()
            .post("/products/" + productName + "/features/" + featureName)
        .then().statusCode(lessThan(300));

        given()
            .formParam("description", "Modified description")
        .when()
            .put("/products/" + productName + "/features/" + featureName)
        .then()
            .statusCode(200);

        given()
        .when()
            .get("/products/" + productName + "/features")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationWithMultipleFeatures_CoversEqualsInFiltering() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature 1")
        .when()
            .post("/products/" + productName + "/features/" + featureName1)
        .then().statusCode(lessThan(300));

        String featureName2 = "Feature2-" + UUID.randomUUID().toString();
        given()
            .formParam("description", "Test feature 2")
        .when()
            .post("/products/" + productName + "/features/" + featureName2)
        .then().statusCode(lessThan(300));

        String configName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName1)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName2)
        .then().statusCode(lessThan(300));

        given()
        .when()
            .get("/products/" + productName + "/configurations/" + configName)
        .then()
            .statusCode(200);
    }
}