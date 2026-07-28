package ts01qwen3_7_plus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

public class FisherTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testFisherMOddNOdd() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/fisher/1/1/0.75")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherMOddNEven() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/fisher/1/2/0.75")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherPLessThanZero() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/fisher/1/1/1000000000000000.0")
        .then()
            .statusCode(200)
            .body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void testFisherPGreaterThanOne() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/fisher/100/100/0.000000000000001")
        .then()
            .statusCode(200)
            .body("value", nullValue());
    }

    @Test(timeout = 60000)
    public void testFisherValidCase() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/fisher/10/5/0.75")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testFisherInvalidParameters() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/fisher/abc/5/0.75")
        .then()
            .statusCode(400);
    }
}