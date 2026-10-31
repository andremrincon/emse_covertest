package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abc/ab")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevNotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abcdef/xyz")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevNotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abcxyz/abc")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundImmediatelyAfter() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abccba/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundLater() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abcxxcba/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatNotFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/cbaxxx/abc")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundImmediatelyAfter() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/cbaabc/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundLater() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/cbaxxxabc/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}