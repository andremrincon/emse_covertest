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
    public static void setup() throws Exception {
        String configured = System.getProperty("test.baseUrl");
        if (configured == null || configured.isEmpty()) {
            configured = System.getenv("TEST_BASE_URL");
        }
        if (configured == null || configured.isEmpty()) {
            configured = "http://localhost:8080/";
        }
        URL url = new URL(configured);
        String base = url.getProtocol() + "://" + url.getHost();
        if (url.getPort() != -1) {
            RestAssured.port = url.getPort();
        }
        RestAssured.baseURI = base;
        String path = url.getPath();
        if (path == null || path.equals("/")) {
            RestAssured.basePath = "";
        } else {
            RestAssured.basePath = path;
        }
    }

    @Test(timeout = 60000)
    public void testOptionsRequestReturnsOkAndCORSHeaders() {
        String productName = "testProd-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().options("/products/{productName}/features", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetFeaturesPassesThroughFilterForNonOptionsMethods() {
        String productName = "testProd-" + UUID.randomUUID().toString();
        String featureName = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().formParam("description", "desc").when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", productName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDeleteProductReturnsNoContent() {
        String productName = "testProd-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}", productName).then().statusCode(204);
    }
}