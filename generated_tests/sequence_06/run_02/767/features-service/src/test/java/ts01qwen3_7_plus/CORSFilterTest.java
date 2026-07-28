package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Test(timeout = 60000)
    public void testDoFilterWithNonOptionsMethod() {
        given()
        .when()
            .get("/products/AeroBook-Pro-15/features")
        .then()
            .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsMethod() {
        given()
        .when()
            .options("/products/AeroBook-Pro-15/features")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testCORSHeadersAreSet() {
        given()
        .when()
            .get("/products/AeroBook-Pro-15/features")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }
}