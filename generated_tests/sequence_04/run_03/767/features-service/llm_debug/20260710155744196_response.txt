package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() throws Exception {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        URL url = new URL(base);
        RestAssured.baseURI = url.getProtocol() + "://" + url.getHost();
        int port = url.getPort();
        if (port == -1) {
            port = url.getDefaultPort();
        }
        RestAssured.port = port;
    }

    @Test(timeout = 60000)
    public void testGetFeaturesInvokesChainAndReturns200() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response r = given().when().get("/products/{productName}/features", product);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequestReturnsCorsHeaders() {
        String product = "prod-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response r = given().when().options("/products/{productName}/features", product);
        r.then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProductReturns201() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response r = given().contentType("application/x-www-form-urlencoded").formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.").when().post("/products/{productName}/features/{featureName}", product, feature);
        r.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturnsNoContent() {
        String product = "prod-" + UUID.randomUUID();
        String feature = "feat-" + UUID.randomUUID();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded").formParam("description", "temp").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response r = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        r.then().statusCode(204);
    }
}