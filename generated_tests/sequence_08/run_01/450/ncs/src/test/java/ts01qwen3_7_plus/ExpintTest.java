package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testExpintNegativeN() {
        int n = -1;
        double x = 2.5;
        given()
            .when()
                .get(BASE_URL + "/api/expint/" + n + "/" + x)
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXAndZeroN() {
        int n = 0;
        double x = 0.0;
        given()
            .when()
                .get(BASE_URL + "/api/expint/" + n + "/" + x)
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroNPositiveX() {
        int n = 0;
        double x = 2.5;
        given()
            .when()
                .get(BASE_URL + "/api/expint/" + n + "/" + x)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintPositiveNXGreaterThanOne() {
        int n = 3;
        double x = 2.5;
        given()
            .when()
                .get(BASE_URL + "/api/expint/" + n + "/" + x)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintPositiveNXLessThanOrEqualToOneNm1Zero() {
        int n = 1;
        double x = 0.1;
        given()
            .when()
                .get(BASE_URL + "/api/expint/" + n + "/" + x)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintPositiveNXLessThanOrEqualToOneNm1NonZero() {
        int n = 3;
        double x = 0.1;
        given()
            .when()
                .get(BASE_URL + "/api/expint/" + n + "/" + x)
            .then()
                .statusCode(200);
    }
}