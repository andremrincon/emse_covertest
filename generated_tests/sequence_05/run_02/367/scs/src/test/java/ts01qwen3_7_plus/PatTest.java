package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
        .when()
            .get("/api/pat/abc/ab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevNotFound() {
        given()
        .when()
            .get("/api/pat/xyz/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevNotFound() {
        given()
        .when()
            .get("/api/pat/abcxyz/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatNotFound() {
        given()
        .when()
            .get("/api/pat/cbaxyz/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevFoundAdjacent() {
        given()
        .when()
            .get("/api/pat/abccba/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndPatRevFoundNotAdjacent() {
        given()
        .when()
            .get("/api/pat/abcxyzcba/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatFoundAdjacent() {
        given()
        .when()
            .get("/api/pat/cbaabc/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevAndPatFoundNotAdjacent() {
        given()
        .when()
            .get("/api/pat/cbaxyzabc/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTxtShorterThanPat() {
        given()
        .when()
            .get("/api/pat/a/abc")
        .then()
            .statusCode(200);
    }
}