package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testGetRequestCoversDoFilterChain() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequestCoversDoFilterElseBranch() {
        given()
            .when()
                .options("/products")
            .then()
                .statusCode(200);
    }
}