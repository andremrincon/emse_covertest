package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testGetRequestCoversIfBranch() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/products")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequestCoversElseBranch() {
        given()
            .baseUri(baseUrl)
        .when()
            .options("/products")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPostRequestCoversFilterChain() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .baseUri(baseUrl)
            .pathParam("productName", productName)
        .when()
            .post("/products/{productName}")
        .then()
            .statusCode(201);
    }
}