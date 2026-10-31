package ts01qwen3_7_plus_2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Test;

public class ExpintTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testExpintErrorNegativeN() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/-1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintErrorX0N1() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/1/0.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintPositiveNX0() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/3/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThan1() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXBetween0And1NGt1() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/3/0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXBetween0And1NEq1() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/1/0.5")
        .then()
            .statusCode(200);
    }
}