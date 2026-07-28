package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class PatTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/pat/abc/ab")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevNotFound() {
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
            .get(baseUrl + "/api/pat/XabcY/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundImmediatelyAfter() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/pat/Xabccba/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundLater() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/pat/XabcYcba/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatNotFound() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/pat/XcbaY/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundImmediatelyAfter() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/pat/Xcbaabc/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundLater() {
        RestAssured.given()
            .when()
            .get(baseUrl + "/api/pat/XcbaYabc/abc")
            .then()
            .statusCode(200);
    }
}