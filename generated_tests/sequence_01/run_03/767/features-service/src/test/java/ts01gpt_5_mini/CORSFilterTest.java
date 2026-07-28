package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        baseURI = env;
    }

    @Test(timeout = 60000)
    public void testGetRequestSetsCORSHeaders() {
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response r = given().when().get("/products/{productName}/features", product);
        r.then().header("Access-Control-Allow-Origin", equalTo("*"));
    }

    @Test(timeout = 60000)
    public void testOptionsRequestReturnsCORSAllowMethodsHeader() {
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response r = given().when().options("/products/{productName}/features", product);
        r.then().header("Access-Control-Allow-Methods", equalTo("POST, PUT, GET, OPTIONS, DELETE"));
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureReturnsNoContent() {
        String product = "test-product-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response r = given().when().delete("/products/{productName}/features/{featureName}", product, feature);
        r.then().statusCode(204);
    }
}