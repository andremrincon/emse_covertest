package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanEqualTwo() {
        given()
            .when()
                .get("/api/pat/abc/ab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverseFound() {
        given()
            .when()
                .get("/api/pat/abcXYZ/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseAdjacentPalindrome() {
        given()
            .when()
                .get("/api/pat/abccba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNonAdjacent() {
        given()
            .when()
                .get("/api/pat/abcXcba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatAdjacent() {
        given()
            .when()
                .get("/api/pat/cbaabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatNonAdjacent() {
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
    public void testTxtShorterThanPat() {
        given()
            .when()
                .get("/api/pat/a/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatTxtOnlyEndpoint() {
        given()
            .when()
                .get("/api/pat/hello")
            .then()
                .statusCode(200);
    }
}