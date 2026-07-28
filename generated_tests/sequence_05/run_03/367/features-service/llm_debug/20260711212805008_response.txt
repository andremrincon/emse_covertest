package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testDoFilterGetRequestChainsThrough() {
        given()
            .when()
                .get("/products")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDoFilterOptionsRequestDoesNotChain() {
        given()
            .when()
                .options("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterPostRequestChainsThrough() {
        String productName = "CORS-Post-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDoFilterDeleteRequestChainsThrough() {
        String productName = "CORS-Del-" + java.util.UUID.randomUUID().toString().substring(0, 8);
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

    @Test(timeout = 60000)
    public void testDoFilterPutRequestChainsThrough() {
        String productName = "CORS-Put-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "test-feature";
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .post("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .put("/products/" + productName + "/features/" + featureName)
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void testDoFilterSetsAllCorsHeaders() {
        given()
            .when()
                .get("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
                .header("Access-Control-Allow-Headers", "x-requested-with")
                .header("Access-Control-Max-Age", "3600");
    }
}