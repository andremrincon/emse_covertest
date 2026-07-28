package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnOptionsRequest() {
        given()
            .header("Origin", "http://example.com")
        .when()
            .options("/products")
        .then()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
            .header("Access-Control-Allow-Headers", "x-requested-with")
            .header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnGetRequest() {
        given()
            .header("Origin", "http://example.com")
        .when()
            .get("/products")
        .then()
            .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testFilterChainsPostRequest() {
        String productName = "CORS-Test-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given()
            .header("Origin", "http://example.com")
        .when()
            .post("/products/" + productName)
        .then()
            .statusCode(201);

        given()
        .when()
            .delete("/products/" + productName)
        .then()
            .statusCode(lessThan(300));
    }
}