package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ProductsConfigurationsServiceTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProductEmpty() {
        String productName = "Product-" + UUID.randomUUID().toString();

        given()
            .pathParam("productName", productName)
        .when()
            .get("/products/{productName}/configurations")
        .then()
            .statusCode(200)
            .body("$.size()", equalTo(0));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsNamesForProductWithConfigurations() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
        .when()
            .get("/products/{productName}/configurations")
        .then()
            .statusCode(200)
            .body("$", hasItem(configName));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNamesEmpty() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
        .then()
            .statusCode(200)
            .body("$.size()", equalTo(0));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesNamesWithFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}/features")
        .then()
            .statusCode(200)
            .body("$", hasItem(featureName));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationSuccess() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddDuplicateFeatureToConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", featureName)
        .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testEvaluateAndUpdateConfigurationValid() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String featureName = "Feature-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", featureName).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", featureName)
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}")
        .then()
            .statusCode(200)
            .body("valid", equalTo(true));
    }

    @Test(timeout = 60000)
    public void testEvaluateAndUpdateConfigurationInvalid() {
        String productName = "Product-" + UUID.randomUUID().toString();
        String configName = "Config-" + UUID.randomUUID().toString();
        String feature1 = "Feature1-" + UUID.randomUUID().toString();
        String feature2 = "Feature2-" + UUID.randomUUID().toString();

        given().pathParam("productName", productName).when().post("/products/{productName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feature1).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("featureName", feature2).when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300));
        given().pathParam("productName", productName).pathParam("configurationName", configName).when().post("/products/{productName}/configurations/{configurationName}").then().statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .formParam("sourceFeature", feature1)
            .formParam("excludedFeature", feature2)
        .when()
            .post("/products/{productName}/constraints/excludes")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", feature1)
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
            .pathParam("featureName", feature2)
        .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}")
        .then()
            .statusCode(500);

        given()
            .pathParam("productName", productName)
            .pathParam("configurationName", configName)
        .when()
            .get("/products/{productName}/configurations/{configurationName}")
        .then()
            .statusCode(200)
            .body("valid", equalTo(false));
    }
}