package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void corsFilter_setsHeadersOnGetRequest() {
        given()
            .header("Origin", "http://example.com")
        .when()
            .get("/products")
        .then()
            .statusCode(200)
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", containsString("GET"));
    }

    @Test(timeout = 60000)
    public void corsFilter_handlesOptionsRequestWithoutForwarding() {
        given()
            .header("Origin", "http://example.com")
            .header("Access-Control-Request-Method", "POST")
        .when()
            .options("/products")
        .then()
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", containsString("POST"))
            .header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void corsFilter_setsHeadersOnPostRequest() {
        String productName = "CORS-Test-Product-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given()
            .header("Origin", "http://example.com")
        .when()
            .post("/products/{productName}", productName)
        .then()
            .statusCode(anyOf(equalTo(201), equalTo(500)))
            .header("Access-Control-Allow-Origin", "*");
    }
}