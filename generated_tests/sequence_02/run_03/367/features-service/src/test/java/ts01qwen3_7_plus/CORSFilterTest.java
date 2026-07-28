package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    private static final String BASE_URI = "http://localhost:8080";

    @Test(timeout = 60000)
    public void testDoFilterWithGetMethod() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/products")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsMethod() {
        given()
            .baseUri(BASE_URI)
        .when()
            .options("/products")
        .then()
            .statusCode(200);
    }
}