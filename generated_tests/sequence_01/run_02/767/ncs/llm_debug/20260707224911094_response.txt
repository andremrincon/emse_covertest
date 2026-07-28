package ts01qwen3_7_plus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Test;

public class ExpintTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testExpintNegativeN() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/-1/1.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroN() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/0/1.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintZeroX() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/2/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOneNm1NotZero() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/3/0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOneNm1Zero() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/expint/1/0.5")
        .then()
            .statusCode(200);
    }
}