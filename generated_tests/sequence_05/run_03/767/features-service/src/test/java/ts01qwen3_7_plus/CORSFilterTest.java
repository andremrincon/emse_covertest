package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testCorsHeadersOnGetRequest() {
        given()
                .when()
                .get("/products")
                .then()
                .header("Access-Control-Allow-Origin", equalTo("*"));
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnOptionsRequest() {
        given()
                .when()
                .options("/products")
                .then()
                .header("Access-Control-Allow-Origin", equalTo("*"));
    }
}