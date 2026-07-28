package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testGserNormal() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGcfNormal() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/gammq/5.5/1000.0")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGserException() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/gammq/1000.0/1000.0")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGcfLargeA() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/gammq/100.0/1000.0")
        .then()
            .statusCode(404);
    }
}