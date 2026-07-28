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
        String base = System.getProperty("base.uri");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URI");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct_returns201() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/features/{featureName}", product, feature);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCreateConfiguration_thenConfigurationIsValid() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        assertEquals(true, resp.jsonPath().getBoolean("valid"));
    }

    @Test(timeout = 60000)
    public void testActivateFeatureThenGetActivedFeatures_containsFeature() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        assertTrue(resp.getBody().asString().contains(feature));
    }

    @Test(timeout = 60000)
    public void testDeactivateFeatureFromConfiguration_returns204() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        assertEquals(204, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGetConfigurationActivedFeatures_empty_when_none() {
        String product = "prod-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config);
        assertEquals(0, resp.jsonPath().getList("$").size());
    }

    @Test(timeout = 60000)
    public void testGetConfiguration_triggersAvailableFeatures_status200() {
        String product = "prod-" + UUID.randomUUID();
        String f1 = "feat-" + UUID.randomUUID();
        String f2 = "feat-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, f1).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, f2).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDeleteProductFeature_returns204() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        assertEquals(204, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration_returns201_when_feature_added() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        String config = "cfg-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        assertEquals(201, resp.getStatusCode());
    }
}