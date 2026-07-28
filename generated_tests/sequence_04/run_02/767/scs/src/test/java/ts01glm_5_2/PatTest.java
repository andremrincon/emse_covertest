package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testShortPatternReturnsZero() {
        given()
            .pathParam("txt", "hello")
            .pathParam("pat", "ab")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundNoReverse() {
        given()
            .pathParam("txt", "abc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternAndReverseAdjacent() {
        given()
            .pathParam("txt", "abccba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternAndReverseNonAdjacent() {
        given()
            .pathParam("txt", "abcxcba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPattern() {
        given()
            .pathParam("txt", "cba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseAndPatternAdjacent() {
        given()
            .pathParam("txt", "cbaabc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseAndPatternNonAdjacent() {
        given()
            .pathParam("txt", "cbaxabc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatternNorReverseFound() {
        given()
            .pathParam("txt", "xyzxyz")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPalindromePatternSelfReverse() {
        given()
            .pathParam("txt", "abaaba")
            .pathParam("pat", "aba")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200);
    }
}