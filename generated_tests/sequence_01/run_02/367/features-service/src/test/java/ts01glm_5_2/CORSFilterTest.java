package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUri = System.getenv().getOrDefault("BASE_URI", "http://localhost");
        String port = System.getenv().getOrDefault("PORT", "8080");
        RestAssured.baseURI = baseUri;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testCORSFilterSetsAllowOriginHeaderOnGetProducts() {
        given()
            .header("Origin", "http://example.com")
        .when()
            .get("/products")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCORSFilterHandlesOptionsPreflightRequest() {
        given()
            .header("Origin", "http://example.com")
            .header("Access-Control-Request-Method", "POST")
            .header("Access-Control-Request-Headers", "x-requested-with")
        .when()
            .options("/products")
        .then()
            .statusCode(lessThan(500))
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testCORSFilterSetsHeadersOnPostProduct() {
        String productName = "CORS-Test-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given()
            .header("Origin", "http://example.com")
        .when()
            .post("/products/" + productName)
        .then()
            .statusCode(201)
            .header("Access-Control-Allow-Origin", "*");
    }
}