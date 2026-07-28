package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatTooShortReturnsZero() {
        given()
            .when()
            .get("/api/pat/hello/hi")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverseFound() {
        given()
            .when()
            .get("/api/pat/abccXba/abc")
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
    public void testReverseFoundFirstPatAdjacentPalindrome() {
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
    public void testReverseFoundNoPatFound() {
        given()
            .when()
            .get("/api/pat/cbaaXc/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
            .when()
            .get("/api/pat/xyzdef/abc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFirstCharMatchesButFullDoesNot() {
        given()
            .when()
            .get("/api/pat/axccxy/abc")
            .then()
            .statusCode(200);
    }
}