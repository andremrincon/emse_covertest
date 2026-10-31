package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testGetRequestPassesThroughCORSFilter() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOptionsRequestHandledByCORSFilter() {
        given()
            .when()
                .options("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCORSAllowOriginHeaderOnGetResponse() {
        given()
            .when()
                .get("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCORSAllowMethodsHeaderOnGetResponse() {
        given()
            .when()
                .get("/products")
            .then()
                .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testPostRequestPassesThroughCORSFilter() {
        String productName = "CORS-Test-" + java.util.UUID.randomUUID().toString();
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteRequestPassesThroughCORSFilter() {
        String productName = "CORS-Test-" + java.util.UUID.randomUUID().toString();
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));
        given()
            .when()
                .delete("/products/" + productName)
            .then()
                .statusCode(204);
    }
}