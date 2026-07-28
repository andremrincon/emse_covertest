package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class PatTest {

    private final String baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/abc/ab")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevNotFound() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/hello/hel")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundImmediatelyAfter() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/abccba/abc")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundLater() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/abcxxcba/abc")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatNotFound() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/cba/abc")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundImmediatelyAfter() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/cbaabc/abc")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundLater() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/cbaxxxabc/abc")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorPatRevFound() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/pat/xxxxx/abc")
                .then()
                .statusCode(200);
    }
}