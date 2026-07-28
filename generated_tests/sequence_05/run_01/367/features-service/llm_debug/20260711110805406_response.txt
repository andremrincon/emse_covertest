package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItem;

public class FeatureTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
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
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProductContainsAddedFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName)
                .then().body("featureName", hasItem((Object) null));
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescriptionReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "initial")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "updated description")
                .when().put("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "toDelete")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddConfigurationAndFeatureToConfigurationReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "cfgfeat")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, featureName)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesReturns200() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configName = "conf-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "cfgfeat")
                .when().post("/products/{productName}/features/{featureName}", productName, featureName)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}",
                productName, configName, featureName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", productName, configName)
                .then().statusCode(200);
    }
}