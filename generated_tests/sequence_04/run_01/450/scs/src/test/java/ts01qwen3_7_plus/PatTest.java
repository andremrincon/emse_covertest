package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatShortPattern() {
        given()
            .when()
                .get("/api/pat/hello/ab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatNoMatch() {
        given()
            .when()
                .get("/api/pat/hello/xyz")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundThenReverseNotAdjacent() {
        given()
            .when()
                .get("/api/pat/abcxcba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundThenReverseAdjacent() {
        given()
            .when()
                .get("/api/pat/abccba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundThenPatNotAdjacent() {
        given()
            .when()
                .get("/api/pat/cbaxabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundThenPatAdjacent() {
        given()
            .when()
                .get("/api/pat/cbaabc/abc")
            .then()
                .statusCode(200);
    }
}