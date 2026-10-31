package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testGetRequestCoversIfBranch() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequestCoversElseBranch() {
        given()
            .when()
                .options("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPostRequestCoversFilterExecution() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(201);
    }
}