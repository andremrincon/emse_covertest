package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliedOnGetProducts() {
        given()
            .header("Origin", "http://example.com")
        .when()
            .get("/products")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliedOnOptionsPreflight() {
        given()
            .header("Origin", "http://example.com")
            .header("Access-Control-Request-Method", "POST")
            .header("Access-Control-Request-Headers", "x-requested-with")
        .when()
            .options("/products")
        .then()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
            .header("Access-Control-Allow-Headers", "x-requested-with")
            .header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void testCORSFilterAppliedOnPostProduct() {
        String productName = "CORS-Test-Product-" + java.util.UUID.randomUUID().toString();
        given()
            .header("Origin", "http://example.com")
        .when()
            .post("/products/{productName}", productName)
        .then()
            .statusCode(201)
            .header("Access-Control-Allow-Origin", "*");
    }
}