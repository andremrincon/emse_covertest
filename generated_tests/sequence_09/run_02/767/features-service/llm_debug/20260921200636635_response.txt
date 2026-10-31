package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        String prop = System.getProperty("api.base");
        String base = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testDeleteProductInvokesFilterChain() {
        String product = "test-delete-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", product).when().delete("/products/{productName}");
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testOptionsRequestHandledWithoutChain() {
        String product = "test-options-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", product).when().options("/products/{productName}/features");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnGetProduct() {
        String product = "test-cors-" + UUID.randomUUID().toString();
        given().pathParam("productName", product).when().post("/products/{productName}").then().statusCode(lessThan(300));
        Response act = given().pathParam("productName", product).when().get("/products/{productName}");
        act.then().header("Access-Control-Allow-Origin", "*");
    }
}