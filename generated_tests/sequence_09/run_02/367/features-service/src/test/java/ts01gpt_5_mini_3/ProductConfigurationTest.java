package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.url");
        if (base == null) base = System.getenv("API_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201() {
        String uuid = UUID.randomUUID().toString();
        String product = "test-product-" + uuid;
        String config = "conf-" + uuid;
        String feature = "feat-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "auto-added feature").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        Assert.assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_returns200() {
        String uuid = UUID.randomUUID().toString();
        String product = "cfg-prod-" + uuid;
        String config = "cfg-" + uuid;
        String feature = "cfg-feature-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "auto-added feature").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetProductFeatures_afterAddingFeatures_returns200() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-features-" + uuid;
        String f1 = "feature-A-" + uuid;
        String f2 = "feature-B-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc A").when().post("/products/{productName}/features/{featureName}", product, f1).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "desc B").when().post("/products/{productName}/features/{featureName}", product, f2).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product);
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_withDescription_returns201() {
        String uuid = UUID.randomUUID().toString();
        String product = "prod-add-" + uuid;
        String feature = "Blood_Oxygen_Sensor_" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.").when().post("/products/{productName}/features/{featureName}", product, feature);
        Assert.assertEquals(201, act.getStatusCode());
    }
}