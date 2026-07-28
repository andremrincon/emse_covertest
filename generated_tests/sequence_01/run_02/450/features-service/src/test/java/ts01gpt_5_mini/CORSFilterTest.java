package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URL;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() throws Exception {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/";
        }
        URL url = new URL(base);
        String protocol = url.getProtocol();
        String host = url.getHost();
        int port = url.getPort();
        String path = url.getPath();
        if (path == null) path = "/";
        RestAssured.baseURI = protocol + "://" + host;
        if (port != -1) RestAssured.port = port;
        RestAssured.basePath = path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
    }

    @Test(timeout = 60000)
    public void testGetFeaturesForProductReturns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", product).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequestIncludesCORSHeaders() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().options("/products/{productName}/features/{featureName}", product, feature).then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturns204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}/features/{featureName}", product, feature).then().statusCode(204);
    }
}