package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCORSFilterSetsHeadersOnGetRequest() {
        given()
            .when()
                .get("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCORSFilterHandlesOptionsRequestWithoutChaining() {
        given()
            .when()
                .options("/products")
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testCORSFilterSetsHeadersOnPostRequest() {
        String productName = "CORS-Test-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        given()
            .when()
                .post("/products/" + productName)
            .then()
                .header("Access-Control-Allow-Origin", "*");
    }
}