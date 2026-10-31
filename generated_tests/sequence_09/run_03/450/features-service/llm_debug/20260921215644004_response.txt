package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testOptionsPreflightSkipsChain() {
        String product = "preflight-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().when().options("/products/{productName}/features", product);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetInvokesChainReturns200() {
        String product = "AeroBook-Pro-15-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCORSHeaderPresentOnGet() {
        String product = "USB-C-Dongle-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().when().get("/products/{productName}/features", product);
        act.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCORSAllowMethodsHeaderOnOptions() {
        String product = "opt-product-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().when().options("/products/{productName}/configurations", product);
        act.then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }
}