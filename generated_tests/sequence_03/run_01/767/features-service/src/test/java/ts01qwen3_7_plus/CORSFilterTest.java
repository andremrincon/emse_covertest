package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testOptionsRequestCorsHeaders() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .options("/products/AeroBook-Pro-15/features")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testGetRequestCorsHeaders() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/products/AeroBook-Pro-15/features")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testPostRequestCorsHeaders() {
        String productName = "TestProduct-" + UUID.randomUUID().toString();
        given()
            .baseUri("http://localhost:8080")
        .when()
            .post("/products/" + productName)
        .then()
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }
}