package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatLenLessThan3() {
        given()
        .when()
            .get("/api/pat/hello/ab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTxtShorterThanPat() {
        given()
        .when()
            .get("/api/pat/ab/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
        .when()
            .get("/api/pat/abcdef/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseAdjacent() {
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
            .get("/api/pat/abcdecba/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPat() {
        given()
        .when()
            .get("/api/pat/cbaxyz/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatAdjacent() {
        given()
        .when()
            .get("/api/pat/cbaabc/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNonAdjacent() {
        given()
        .when()
            .get("/api/pat/cbaxabc/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
        .when()
            .get("/api/pat/xyz123/abc")
        .then()
            .statusCode(200);
    }
}