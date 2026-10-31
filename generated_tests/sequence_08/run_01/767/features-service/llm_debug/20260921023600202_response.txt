package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCorsFilterWithGetRequest() {
        given()
            .when()
            .get("/products")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterWithOptionsRequest() {
        given()
            .when()
            .options("/products")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterWithPostRequest() {
        String productName = "TestProduct-" + System.currentTimeMillis();
        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(201);
    }
}