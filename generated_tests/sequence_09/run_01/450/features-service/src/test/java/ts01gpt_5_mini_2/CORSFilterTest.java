package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() throws MalformedURLException {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", System.getenv("api.base"));
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        URL url = new URL(base);
        RestAssured.baseURI = url.getProtocol() + "://" + url.getHost();
        if (url.getPort() != -1) {
            RestAssured.port = url.getPort();
        }
        RestAssured.basePath = (url.getPath() == null || url.getPath().isEmpty()) ? "/" : url.getPath();
    }

    @Test(timeout = 60000)
    public void testOptionsCorsHeaders() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().options("/products/{productName}/features", product).then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testGetFeaturesInvokesFilterChain() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}/features", product).then().statusCode(200);
    }
}