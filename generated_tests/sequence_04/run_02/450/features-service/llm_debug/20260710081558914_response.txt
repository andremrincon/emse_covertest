package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProductReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String f1 = "featA-" + UUID.randomUUID().toString();
        String f2 = "featB-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "d1").when().post("/products/{productName}/features/{featureName}", product, f1).then().statusCode(lessThan(300));
        given().formParam("description", "d2").when().post("/products/{productName}/features/{featureName}", product, f2).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", product).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddConfigurationReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationByNameReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeaturesContainsFeature() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().body("$", hasItem(feature));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationReturns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteConfigurationReturns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationByNameReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProductReturns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureOfProductReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "initial").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().formParam("description", "updated description").when().put("/products/{productName}/features/{featureName}", product, feature).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProductReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String c1 = "confA-" + UUID.randomUUID().toString();
        String c2 = "confB-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, c1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, c2).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/configurations", product).then().statusCode(200);
    }
}