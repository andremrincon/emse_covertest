package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CORSFilterTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testCorsFilterWithV1All() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterWithV2All() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v2/all")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterWithV1Alpha() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterWithV2Alpha() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }
}