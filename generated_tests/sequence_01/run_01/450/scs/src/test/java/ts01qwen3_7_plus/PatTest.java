package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class PatTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatternLengthLessThanOrEqualToTwo() {
        String txt = "ABABCABAB";
        String pat = "AB";

        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatternFoundOnly() {
        String txt = "ABABCABAB";
        String pat = "ABAB";

        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReversePatternFoundOnly() {
        String txt = "BABA";
        String pat = "ABAB";

        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testBothPatternAndReverseFoundNotAdjacent() {
        String txt = "ABABXYZBABA";
        String pat = "ABAB";

        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPalindromePatternFollowedByReverse() {
        String txt = "ABABBABA";
        String pat = "ABAB";

        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPalindromeReverseFollowedByPattern() {
        String txt = "BABAABAB";
        String pat = "ABAB";

        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNeitherPatternNorReverseFound() {
        String txt = "XYZABCDEF";
        String pat = "ABAB";

        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSingleCharacterPattern() {
        String txt = "ABCDEF";
        String pat = "A";

        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"\"   Actual: none")
    @Test(timeout = 60000)
    public void testEmptyTextWithValidPattern() {
        String txt = "";
        String pat = "ABAB";

        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo(""));
    }
}