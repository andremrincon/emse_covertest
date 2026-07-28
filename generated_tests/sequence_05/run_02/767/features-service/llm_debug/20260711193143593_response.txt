package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductConfigurationTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCreateConfigurationSetsValidTrue() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(201);

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationActivatesFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfigurationDeactivatesFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetActiveFeaturesReturnsActivatedFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
            .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(1)));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturnsProductAssociation() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesReturnsProductFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        String featureName2 = "Feature2-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName1", featureName1)
            .when()
            .post("/products/{productName}/features/{featureName1}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName2", featureName2)
            .when()
            .post("/products/{productName}/features/{featureName2}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .when()
            .get("/products/{productName}/features")
            .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(2)));
    }

    @Test(timeout = 60000)
    public void testHasActiveFeatureReturnsTrueForActiveFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features");

        response.then()
            .statusCode(200)
            .body("$", hasItem(featureName));
    }

    @Test(timeout = 60000)
    public void testHasActiveFeatureReturnsFalseForInactiveFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        String featureName2 = "Feature2-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName1", featureName1)
            .when()
            .post("/products/{productName}/features/{featureName1}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName2", featureName2)
            .when()
            .post("/products/{productName}/features/{featureName2}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName1", featureName1)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName1}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features");

        response.then()
            .statusCode(200)
            .body("$", not(hasItem(featureName2)));
    }

    @Test(timeout = 60000)
    public void testCollectFeatureNamesReturnsFeatureNameSet() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName", featureName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features");

        response.then()
            .statusCode(200)
            .body("[0]", equalTo(featureName));
    }

    @Test(timeout = 60000)
    public void testMultipleFeaturesCanBeActivated() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        String featureName2 = "Feature2-" + UUID.randomUUID().toString();
        String featureName3 = "Feature3-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName1", featureName1)
            .when()
            .post("/products/{productName}/features/{featureName1}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName2", featureName2)
            .when()
            .post("/products/{productName}/features/{featureName2}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName3", featureName3)
            .when()
            .post("/products/{productName}/features/{featureName3}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName1", featureName1)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName1}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName2", featureName2)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName2}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName3", featureName3)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName3}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features");

        response.then()
            .statusCode(200)
            .body("$", hasSize(3));
    }

    @Test(timeout = 60000)
    public void testDeactivateRemovesFeatureFromActiveSet() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();
        String featureName1 = "Feature1-" + UUID.randomUUID().toString();
        String featureName2 = "Feature2-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName1", featureName1)
            .when()
            .post("/products/{productName}/features/{featureName1}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("featureName2", featureName2)
            .when()
            .post("/products/{productName}/features/{featureName2}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName1", featureName1)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName1}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName2", featureName2)
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName2}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .pathParam("featureName1", featureName1)
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName1}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features");

        response.then()
            .statusCode(200)
            .body("$", hasSize(1))
            .body("$", hasItem(featureName2));
    }

    @Test(timeout = 60000)
    public void testEmptyConfigurationReturnsEmptyActiveFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features");

        response.then()
            .statusCode(200)
            .body("$", hasSize(0));
    }

    @Test(timeout = 60000)
    public void testConfigurationValidFlagIsSetOnCreation() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .get("/products/{productName}/configurations/{configurationName}");

        response.then()
            .statusCode(200)
            .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testGetProductReturnsAssociatedProduct() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
            .when()
            .post("/products/{productName}")
            .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configurationName)
            .when()
            .post("/products/{productName}/configurations/{configurationName}")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .pathParam("productName", productName)
            .when()
            .get("/products/{productName}");

        response.then()
            .statusCode(200)
            .body("name", equalTo(productName));
    }
}