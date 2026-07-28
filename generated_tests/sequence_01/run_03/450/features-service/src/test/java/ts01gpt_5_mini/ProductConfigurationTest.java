package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ProductConfigurationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWhenProductHasFeatures() {
        String product = "prod-" + UUID.randomUUID().toString().replace("-", "");
        String config = "cfg-" + UUID.randomUUID().toString().replace("-", "");
        String feature = "feat-" + UUID.randomUUID().toString().replace("-", "");
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        Assert.assertFalse(resp.getBody().asString().contains(feature));
    }

    @Test(timeout = 60000)
    public void testAvailableFeaturesWhenProductHasNoFeatures() {
        String product = "prod-" + UUID.randomUUID().toString().replace("-", "");
        String config = "cfg-" + UUID.randomUUID().toString().replace("-", "");
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/configurations/{configurationName}", product, config);
        Assert.assertTrue(resp.getBody().asString().contains("activedFeatures") && (resp.getBody().asString().contains("[]") || !resp.getBody().asString().contains("feature")));
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationReturns201() {
        String product = "prod-" + UUID.randomUUID().toString().replace("-", "");
        String config = "cfg-" + UUID.randomUUID().toString().replace("-", "");
        String feature = "feat-" + UUID.randomUUID().toString().replace("-", "");
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", product, config).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
        Assert.assertEquals(201, resp.getStatusCode());
    }
}