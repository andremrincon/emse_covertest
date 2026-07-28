package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String description = "Desc " + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", description)
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProductReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        when().get("/products/{productName}/features", product).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "initial")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "updated")
                .when().put("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "to-delete")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "cfg-feature")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature)
                .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeaturesReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String configuration = "conf-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, configuration)
                .then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "cfg-feature")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature)
                .then().statusCode(lessThan(300));
        when().get("/products/{productName}/configurations/{configurationName}/features", product, configuration)
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCreateProductReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        when().post("/products/{productName}", product).then().statusCode(201);
    }
}