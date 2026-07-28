package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCORSFilterPassesNonOptionsRequestThroughChain() {
        given()
            .header("Origin", "http://example.com")
        .when()
            .get("/products")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCORSFilterHandlesOptionsRequestWithoutChaining() {
        given()
            .header("Origin", "http://example.com")
            .header("Access-Control-Request-Method", "GET")
        .when()
            .request(Method.OPTIONS, "/products")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCORSFilterSetsAllowMethodsHeaderOnResponse() {
        given()
            .header("Origin", "http://example.com")
        .when()
            .get("/products")
        .then()
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }
}