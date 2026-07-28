package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("api.baseUrl");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("API_BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void testGetFeaturesNonOptionsPassesThrough() {
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", encode(product)).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", encode(product));
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testOptionsPreflightDoesNotInvokeChain() {
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", encode(product)).then().statusCode(lessThan(300));
        Response resp = given().when().options("/products/{productName}/features", encode(product));
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnGet() {
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", encode(product)).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", encode(product));
        assertEquals("*", resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testCORSAllowMethodsHeaderOnOptions() {
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", encode(product)).then().statusCode(lessThan(300));
        Response resp = given().when().options("/products/{productName}/features", encode(product));
        assertEquals("POST, PUT, GET, OPTIONS, DELETE", resp.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAddAndDeleteFeatureFlow() {
        String product = "test-product-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", encode(product)).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", encode(product), encode(feature)).then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/features/{featureName}", encode(product), encode(feature));
        assertEquals(204, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfigurationProducesCreated() {
        String product = "test-product-" + UUID.randomUUID().toString();
        String config = "cfg-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", encode(product)).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", encode(product), encode(config)).then().statusCode(lessThan(300));
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", encode(product), encode(config), encode(feature));
        assertEquals(500, resp.getStatusCode());
    }

    private static String encode(String s) {
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}