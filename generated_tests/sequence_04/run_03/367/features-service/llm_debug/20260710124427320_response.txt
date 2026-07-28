package ts01gpt_5_mini;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    private static final String BASE_URL;
    static {
        String url = System.getProperty("base.url");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        BASE_URL = url;
    }

    @Test(timeout = 60000)
    public void testGetFeaturesIncludesAccessControlAllowOriginHeader() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE_URL).when().get("/products/{productName}/features", productName).then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testOptionsPreflightReturns200ForFeaturesEndpoint() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE_URL).when().options("/products/{productName}/features", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsPreflightIncludesAllowMethodsHeader() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE_URL).when().options("/products/{productName}/features", productName).then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE_URL).contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturns204() {
        String productName = "test-product-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().baseUri(BASE_URL).when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().baseUri(BASE_URL).contentType("application/x-www-form-urlencoded").formParam("description", "to delete").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().baseUri(BASE_URL).when().delete("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(204);
    }
}