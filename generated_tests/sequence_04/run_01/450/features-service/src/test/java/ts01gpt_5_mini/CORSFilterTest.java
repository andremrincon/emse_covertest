package ts01gpt_5_mini;

import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String fromSys = System.getProperty("test.server.base");
        String fromEnv = System.getenv("TEST_SERVER_BASE");
        if (fromSys != null && !fromSys.isEmpty()) {
            BASE = fromSys;
        } else if (fromEnv != null && !fromEnv.isEmpty()) {
            BASE = fromEnv;
        } else {
            BASE = "http://localhost:8080";
        }
    }

    private static String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }

    @Test(timeout = 60000)
    public void testGetProductPassesThroughAndSetsAllowOriginHeader() {
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post(BASE + "/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().get(BASE + "/products/" + enc(product)).then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testOptionsRequestReturnsCORSAllowMethodsHeader() {
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post(BASE + "/products/" + enc(product)).then().statusCode(lessThan(300));
        given().when().options(BASE + "/products/" + enc(product) + "/features").then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testAddFeatureReturnsCreatedStatus() {
        String product = "test-product-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post(BASE + "/products/" + enc(product)).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.").when().post(BASE + "/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturnsNoContent() {
        String product = "test-product-" + UUID.randomUUID().toString();
        String feature = "feature-" + UUID.randomUUID().toString();
        given().when().post(BASE + "/products/" + enc(product)).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("description", "temp").when().post(BASE + "/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(lessThan(300));
        given().when().delete(BASE + "/products/" + enc(product) + "/features/" + enc(feature)).then().statusCode(204);
    }
}