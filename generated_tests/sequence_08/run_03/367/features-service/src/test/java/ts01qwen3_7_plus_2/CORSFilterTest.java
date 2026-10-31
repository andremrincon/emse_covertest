package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCORSFilterWithGetRequest() {
        String productName = "Product-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}", productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCORSFilterWithOptionsRequest() {
        given()
            .when()
            .options("/products")
            .then()
            .header("Access-Control-Allow-Methods", equalTo("POST, PUT, GET, OPTIONS, DELETE"));
    }

    @Test(timeout = 60000)
    public void testCORSFilterHeadersPresent() {
        String productName = "Product-" + UUID.randomUUID().toString();

        given()
            .when()
            .post("/products/{productName}", productName)
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/products/{productName}", productName)
            .then()
            .header("Access-Control-Allow-Origin", equalTo("*"));
    }
}