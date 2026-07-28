package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateProductReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("description", "desc").when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetProductFeaturesContainsAddedFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("description", "desc").when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().body(containsString(featureName));
    }

    @Test(timeout = 60000)
    public void testCreateConfigurationReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("description", "d").when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testConfigurationFeaturesListReflectsActivatedFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("description", "d").when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then().body(containsString(featureName));
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromConfigurationReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("description", "d").when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(lessThan(300));
        given().when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testConfigurationFeaturesListDoesNotContainAfterRemoval() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("description", "d").when()
            .post("/products/{productName}/features/{featureName}", productName, featureName)
            .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));
        given().when()
            .post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(lessThan(300));
        given().when()
            .delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName)
            .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
            .then().body(not(containsString(featureName)));
    }

    @Test(timeout = 60000)
    public void testDeleteConfigurationReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName)
            .then().statusCode(200);
    }
}