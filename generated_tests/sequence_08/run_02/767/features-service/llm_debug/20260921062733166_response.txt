package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testDoFilterTrueBranch() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterFalseBranch() {
        given()
            .when()
                .options("/products")
            .then()
                .statusCode(200);
    }
}