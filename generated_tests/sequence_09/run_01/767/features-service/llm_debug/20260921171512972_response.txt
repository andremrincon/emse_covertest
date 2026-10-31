package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetConfigurationReturns200AndExercisesAvailableFeatures() {
        String uuid = UUID.randomUUID().toString();
        String product = "MobileApp-Backend-" + uuid;
        String config = "production-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("description", "Fingerprint scanner feature").when().post("/products/{productName}/features/{featureName}", product, "fingerprint-scanner").then().statusCode(lessThan(300));
        given().formParam("description", "Backlit keyboard feature").when().post("/products/{productName}/features/{featureName}", product, "backlit-keyboard").then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String uuid = UUID.randomUUID().toString();
        String product = "QuantumLeap-AI-Platform-" + uuid;
        String config = "standard-gpu-cluster-" + uuid;
        String feature = "distributed-training-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("description", "Distributed training feature").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        Assert.assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfigurationReturns204() {
        String uuid = UUID.randomUUID().toString();
        String product = "QuantumLeap-AI-" + uuid;
        String config = "Premium-Tier-" + uuid;
        String feature = "Realtime-Analytics-Dashboard-" + uuid;
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().formParam("description", "Realtime analytics").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        Assert.assertEquals(204, act.getStatusCode());
    }
}