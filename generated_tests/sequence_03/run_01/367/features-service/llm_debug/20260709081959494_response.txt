package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeature_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "initial")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "updated")
                .when().put("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeature_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "to-delete")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "cfgfeat")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_returns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "cfgadd")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_returns200_and_containsFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "act")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName)
                .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
                .then().body("$", hasItem(featureName));
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProduct_returns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "cfg-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName)
                .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductByName_returns200_and_bodyMatches() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", productName).then().body("name", equalTo(productName));
    }
}