package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static final String BASE_URL;
    static {
        String v = System.getProperty("API_BASE_URL");
        if (v == null || v.isEmpty()) v = System.getenv("API_BASE_URL");
        if (v == null || v.isEmpty()) v = "http://localhost:8080";
        BASE_URL = v;
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testNonOptionsRequestIncludesAllowOriginHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testOptionsRequestIncludesAllowMethodsHeader() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().options("/products/{productName}/features", productName).then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(201);
    }
}