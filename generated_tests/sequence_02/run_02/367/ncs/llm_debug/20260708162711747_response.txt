package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Test(timeout = 60000)
    public void testExpintInvalidN() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintN0() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/0/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintX0() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/2/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThan1() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOrEqualTo1() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/3/0.1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintN1XLessThanOrEqualTo1() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/1/0.1")
            .then()
                .statusCode(200);
    }
}