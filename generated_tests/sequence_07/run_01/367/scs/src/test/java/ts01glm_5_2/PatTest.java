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
    public void testPatLenLessOrEqualTwo() {
        given()
            .when()
                .get("/api/pat/abc/ab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .when()
                .get("/api/pat/ABCDEF/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseAdjacent() {
        given()
            .when()
                .get("/api/pat/ABCCBA/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNonAdjacent() {
        given()
            .when()
                .get("/api/pat/ABCXCBA/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatAdjacent() {
        given()
            .when()
                .get("/api/pat/CBAABC/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatNonAdjacent() {
        given()
            .when()
                .get("/api/pat/CBAXABC/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPat() {
        given()
            .when()
                .get("/api/pat/CBADEF/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherFoundPatLenGreaterThanTwo() {
        given()
            .when()
                .get("/api/pat/XYZXYZ/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCharMatchesPatRevFirstCharButNoFullMatch() {
        given()
            .when()
                .get("/api/pat/CDEFGH/ABC")
            .then()
                .statusCode(200);
    }
}