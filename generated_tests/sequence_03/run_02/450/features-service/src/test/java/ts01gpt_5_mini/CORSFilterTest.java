package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
            if (base == null || base.isEmpty()) {
                base = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void optionsPreflightOnProductFeatures_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().when().options("/products/{productName}/features", product);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void getFeatures_afterAddingFeature_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        String feature = "feat-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}/features", product);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void optionsPreflightOnProduct_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().when().options("/products/{productName}", product);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void getProduct_afterCreation_returns200() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response resp = given().when().get("/products/{productName}", product);
        assertEquals(200, resp.getStatusCode());
    }
}