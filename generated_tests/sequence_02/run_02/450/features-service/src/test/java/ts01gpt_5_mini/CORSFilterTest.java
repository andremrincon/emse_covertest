package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CORSFilterTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateAndGetProduct_returns200() {
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", product).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequest_setsCORSHeaders() {
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().options("/products/{productName}", product).then().header("Access-Control-Allow-Origin", equalTo("*"));
    }

    @Test(timeout = 60000)
    public void testDeleteProduct_returns204() {
        String product = "test-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}", product).then().statusCode(204);
    }
}