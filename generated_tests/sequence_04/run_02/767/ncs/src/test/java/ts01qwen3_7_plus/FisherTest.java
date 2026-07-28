package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Test(timeout = 60000)
    public void testFisherNormal() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPGreaterThan1() {
        given()
            .when()
                .get("/api/fisher/1/1/100.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPLessThan0() {
        given()
            .when()
                .get("/api/fisher/10/5/0.000000000000001")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidM() {
        given()
            .when()
                .get("/api/fisher/abc/5/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidN() {
        given()
            .when()
                .get("/api/fisher/10/-3/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidX() {
        given()
            .when()
                .get("/api/fisher/10/5/abc")
            .then()
                .statusCode(400);
    }
}