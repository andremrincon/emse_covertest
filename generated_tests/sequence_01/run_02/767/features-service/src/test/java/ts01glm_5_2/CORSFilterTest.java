package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDoFilterWithNonOptionsRequest() {
        given()
            .header("Origin", "http://example.com")
        .when()
            .get("/products")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsRequest() {
        given()
            .header("Origin", "http://example.com")
            .header("Access-Control-Request-Method", "GET")
        .when()
            .options("/products")
        .then()
            .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void testDoFilterWithPostRequest() {
        String productName = "CORS-Test-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given()
            .header("Origin", "http://example.com")
        .when()
            .post("/products/" + productName)
        .then()
            .statusCode(201);
    }
}