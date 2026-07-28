package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class PatTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/abc/ab")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTxtLenLessThanPatLen() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/ab/abcd")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatNotFound() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/abcdef/xyz")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevNotFound() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/abcxyz/abc")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatNotFound() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/cbaxyz/abc")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundAndPatRevFoundImmediatelyAfter() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/abccba/abc")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundAndPatRevFoundLater() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/abcxxcba/abc")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundAndPatFoundImmediatelyAfter() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/cbaabc/abc")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundAndPatFoundLater() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/cbaxxabc/abc")
                .then()
                .statusCode(200);
    }
}