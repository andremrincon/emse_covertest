package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URI;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() throws Exception {
        String base = System.getProperty("API_BASE_URL");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = "http://localhost:8080";
        URI uri = new URI(base);
        String scheme = uri.getScheme() == null ? "http" : uri.getScheme();
        String host = uri.getHost() == null ? "localhost" : uri.getHost();
        RestAssured.baseURI = scheme + "://" + host;
        if (uri.getPort() != -1) RestAssured.port = uri.getPort();
    }

    @Test(timeout = 60000)
    public void optionsRequestShouldReturnCorsHeaders() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().when().options("/products/{productName}", product);
        resp.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void getFeaturesShouldPassThroughFilterAndReturn200() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", product);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void addFeatureToProductShouldReturn201() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "Test feature")
                .when().post("/products/{productName}/features/{featureName}", product, feature);
        resp.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void deleteFeatureShouldReturn204() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "To be deleted")
                .when().post("/products/{productName}/features/{featureName}", product, feature)
                .then().statusCode(lessThan(300));
        Response resp = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        resp.then().statusCode(204);
    }
}