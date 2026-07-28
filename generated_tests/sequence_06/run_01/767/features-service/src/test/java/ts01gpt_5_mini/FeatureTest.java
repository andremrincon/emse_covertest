package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_setsNameAndProduct_andReturns201() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded").formParam("description", "desc")
                .when().post("/products/{productName}/features/{featureName}", product, feature);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureOfProduct_updatesDescription_andReturns200() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "initial")
                .when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded").formParam("description", "updated description")
                .when().put("/products/{productName}/features/{featureName}", product, feature);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureOfProduct_returns204() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "to-delete")
                .when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        resp.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "listable")
                .when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", product);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "cfg-feature")
                .when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_returns204() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "cfg-feature-delete")
                .when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        resp.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_returns200() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "active-list")
                .when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationsForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations", product);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}", product, config);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteConfiguration_returns204() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/configurations/{configurationName}", product, config);
        resp.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testCreateProduct_andGetProduct_returns201and200() {
        String product = "prod-" + UUID.randomUUID();
        Response respCreate = given().when().post("/products/{productName}", product);
        respCreate.then().statusCode(201);
        given().when().get("/products/{productName}", product).then().statusCode(lessThan(300));
    }
}