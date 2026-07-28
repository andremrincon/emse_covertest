package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDoFilterWithGetMethod() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsMethod() {
        given()
            .when()
                .options("/products")
            .then()
                .statusCode(200);
    }
}