package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNonOptionsRequestInvokesChainDoFilter() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString();
        String encName = URLEncoder.encode(productName, "UTF-8");
        given().when().post("/products/{productName}", encName).then().statusCode(lessThan(300));
        given().when().get("/products/{productName}", encName).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequestSetsCORSHeadersWithoutInvokingChain() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString();
        String encName = URLEncoder.encode(productName, "UTF-8");
        given().when().post("/products/{productName}", encName).then().statusCode(lessThan(300));
        given().when().options("/products/{productName}", encName).then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testDeleteProductReturnsNoContent() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString();
        String encName = URLEncoder.encode(productName, "UTF-8");
        given().when().post("/products/{productName}", encName).then().statusCode(lessThan(300));
        given().when().delete("/products/{productName}", encName).then().statusCode(204);
    }
}