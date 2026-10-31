package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductCreatesFeatureWithNameAndProduct() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        given()
            .formParam("description", "Test description for feature")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescriptionCallsSetName() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
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
    public void testGetFeaturesForProductTriggersEqualsInSet() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature description")
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
    public void testAddFeatureToConfigurationTriggersEquals() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddSameFeatureToConfigurationTwiceTriggersEqualsTrueBranch() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));
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
    public void testGetConfigurationFeaturesTriggersEquals() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationTriggersEquals() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "TestConfig-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));
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
    public void testDeleteFeatureFromProductTriggersEquals() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature description")
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
    public void testGetProductByNameTriggersGetProduct() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature description")
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
    public void testAddMultipleFeaturesTriggersEqualsWithNameMismatch() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName1 = "TestFeatA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName2 = "TestFeatB-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature A description")
            .when()
            .post("/products/" + productName + "/features/" + featureName1)
            .then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature B description")
            .when()
            .post("/products/" + productName + "/features/" + featureName2)
            .then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/" + productName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToMultipleConfigurationsTriggersEquals() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String configName1 = "TestConfigA-" + UUID.randomUUID().toString().substring(0, 8);
        String configName2 = "TestConfigB-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName1).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configName2).then().statusCode(lessThan(300));
        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName1 + "/features/" + featureName)
            .then().statusCode(lessThan(300));
        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configName2 + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteProductWithFeaturesTriggersEquals() {
        String productName = "TestProd-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "TestFeat-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given()
            .formParam("description", "Feature description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/" + productName)
            .then()
            .statusCode(204);
    }
}