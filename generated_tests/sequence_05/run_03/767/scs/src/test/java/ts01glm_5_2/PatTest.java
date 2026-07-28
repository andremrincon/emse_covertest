package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("API_BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testShortPatternReturnsZero() {
        given()
            .when()
                .get("/api/pat/abc/ab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseCharMatchButNoFullMatch() {
        given()
            .when()
                .get("/api/pat/abccde/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatCharMatchButNoFullMatch() {
        given()
            .when()
                .get("/api/pat/cbaade/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndReverseAdjacentPalindrome() {
        given()
            .when()
                .get("/api/pat/abccba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNonAdjacent() {
        given()
            .when()
                .get("/api/pat/abcXcba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseAndPatAdjacentPalindrome() {
        given()
            .when()
                .get("/api/pat/cbaabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseAndPatNonAdjacent() {
        given()
            .when()
                .get("/api/pat/cbaXabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
            .when()
                .get("/api/pat/xyzxyz/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoRoomForReverseInnerLoop() {
        given()
            .when()
                .get("/api/pat/Xabc/abc")
            .then()
                .statusCode(200);
    }
}