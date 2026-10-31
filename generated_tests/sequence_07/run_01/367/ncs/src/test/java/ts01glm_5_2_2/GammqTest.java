package ts01glm_5_2_2;

import static io.restassured.RestAssured.given;
import org.junit.Test;

public class GammqTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testGserNormalConvergence() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserWithXZero() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/gammq/5.5/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfPath() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserNonConvergence() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/gammq/1000.0/999.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidA() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/gammq/-1.0/2.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidX() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/gammq/5.5/-1.0")
        .then()
            .statusCode(400);
    }
}