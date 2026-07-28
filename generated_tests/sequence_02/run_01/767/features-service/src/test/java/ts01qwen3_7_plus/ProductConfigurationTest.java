package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class ProductConfigurationTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSetValidWhenCreatingConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String configurationName = "Config-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductWhenGettingConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String configurationName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testActiveFeatureWhenAddingFeatureToConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        String configurationName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeactiveFeatureWhenRemovingFeatureFromConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        String configurationName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testActivedFeaturesWhenGettingActiveFeatures() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        String configurationName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
            .statusCode(200)
            .body("$", hasItem(featureName));
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWhenGettingConfiguration() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        String configurationName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}", productName, configurationName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testHasActiveFeatureAfterRemovingFeature() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String featureName = "Feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        String configurationName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
            .statusCode(200)
            .body("$", not(hasItem(featureName)));
    }

    @Test(timeout = 60000)
    public void testCollectFeatureNamesWithEmptySet() {
        String productName = "Product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        String configurationName = "Config-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
            .statusCode(200)
            .body("$", hasSize(0));
    }
}