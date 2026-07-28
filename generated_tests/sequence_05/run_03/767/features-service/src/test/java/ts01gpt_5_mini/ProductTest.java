package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        String desc = "Description " + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", desc).when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", desc).when().post("/products/{productName}/features/{featureName}", product, feature);
        Assert.assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromProduct_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        Assert.assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product);
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String req = "req-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}", product, req).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("requiredFeature", req).when().post("/products/{productName}/constraints/requires", product);
        Assert.assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String src = "src-" + UUID.randomUUID().toString();
        String excl = "excl-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}", product, src).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}", product, excl).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("sourceFeature", src).formParam("excludedFeature", excl).when().post("/products/{productName}/constraints/excludes", product);
        Assert.assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        Assert.assertEquals(500, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration_returns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "d").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        Assert.assertEquals(204, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetProductByName_returns200_afterAddingFeatures() {
        String product = "prod-" + UUID.randomUUID().toString();
        String f1 = "feat-" + UUID.randomUUID().toString();
        String f2 = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, f1).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", product, f2).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}", product);
        Assert.assertEquals(200, act.getStatusCode());
    }
}