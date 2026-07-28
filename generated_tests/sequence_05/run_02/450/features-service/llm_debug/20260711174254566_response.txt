package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesIncludesProductFeatures() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureA = "featureA-" + UUID.randomUUID().toString();
        String featureB = "featureB-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWhenNoProductFeaturesReturns200() throws Exception {
        String productName = "prod-empty-" + UUID.randomUUID().toString();
        String configName = "cfg-empty-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        String configName = "cfg-" + UUID.randomUUID().toString();

        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(lessThan(300));

        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName);
        assertEquals(201, resp.getStatusCode());
    }
}