package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDoFilterWithGetRequest() {
        given()
            .when()
            .get("/products")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsRequest() {
        given()
            .when()
            .options("/products")
            .then()
            .statusCode(200);
    }
}