package ts01glm_5_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {
    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testExpintErrorPathNegativeN() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/-1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNZero() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/0/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNNotZeroOrOne() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/2/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionConverges() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1NotZero() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/3/0.1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1Zero() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/1/0.1")
        .then()
            .statusCode(200);
    }
}