package ts01glm_5_2;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqual2() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/hello/ab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/ABCDEF/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseAdjacent() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/ABCCBA/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNonAdjacent() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/ABCXCBA/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPat() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/CBADEF/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatAdjacent() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/CBAABC/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNonAdjacent() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/CBAXABC/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/XYZXYZ/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPartialMatchNoFullMatch() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/ACBCXY/ABC")
        .then()
            .statusCode(200);
    }
}