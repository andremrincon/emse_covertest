package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithGetMethod() {
        RestAssured.given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsMethod() {
        RestAssured.given()
                .when()
                .options("/products")
                .then()
                .statusCode(200);
    }
}