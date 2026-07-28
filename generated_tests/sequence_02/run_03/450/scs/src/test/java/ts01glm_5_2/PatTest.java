package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatLenLessOrEqualTwo() {
        given()
            .when()
                .get("/api/pat/ABC/ab")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .when()
                .get("/api/pat/ABC/ABC")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPat() {
        given()
            .when()
                .get("/api/pat/CBA/ABC")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPalindromePatFollowedByReverse() {
        given()
            .when()
                .get("/api/pat/ABCCBA/ABC")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatThenReverseNonAdjacent() {
        given()
            .when()
                .get("/api/pat/XABCXCBA/ABC")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPalindromeReverseFollowedByPat() {
        given()
            .when()
                .get("/api/pat/CBAABC/ABC")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseThenPatNonAdjacent() {
        given()
            .when()
                .get("/api/pat/XCBAXABC/ABC")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
            .when()
                .get("/api/pat/XYZ/ABC")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}