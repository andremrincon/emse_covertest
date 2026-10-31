package ts01qwen3_7_plus_3;

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

    @Test(timeout = 60000)
    public void testPostProduct() {
        String productName = "TestProduct-" + System.currentTimeMillis();

        given()
                .when()
                .post("/products/" + productName)
                .then()
                .statusCode(201);
    }
}