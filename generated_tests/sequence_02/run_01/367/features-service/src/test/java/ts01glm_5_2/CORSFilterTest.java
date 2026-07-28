package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCORSHeadersPresentOnGetProducts() {
        given()
            .header("Origin", "http://example.com")
        .when()
            .get("/products")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCORSFilterPassesNonOptionsRequestThroughChain() {
        String productName = "cors-chain-test-" + java.util.UUID.randomUUID().toString();

        given()
            .header("Origin", "http://example.com")
        .when()
            .post("/products/" + productName)
        .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCORSFilterHandlesOptionsRequestWithoutChaining() {
        given()
            .header("Origin", "http://example.com")
        .when()
            .request(Method.OPTIONS, "/products")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }
}