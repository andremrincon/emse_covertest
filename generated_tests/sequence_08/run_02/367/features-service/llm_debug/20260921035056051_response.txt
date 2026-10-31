package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
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

    @Test(timeout = 60000)
    public void testDoFilterWithPostMethod() {
        given()
                .pathParam("productName", "Wireless Mouse")
                .when()
                .post("/products/{productName}")
                .then()
                .statusCode(500);
    }
}