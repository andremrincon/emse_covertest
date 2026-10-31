package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @Before
    public void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
        RestAssured.basePath = "";
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
    public void testTxtLenLessThanPatLen() {
        given()
            .when()
            .get("/api/pat/AB/ABC")
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
    public void testPalindromePatFollowedByReverse() {
        given()
            .when()
            .get("/api/pat/ABCCBA/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNotAdjacent() {
        given()
            .when()
            .get("/api/pat/ABCXCBA/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFirstPatAdjacent() {
        given()
            .when()
            .get("/api/pat/CBAABC/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFirstPatNotAdjacent() {
        given()
            .when()
            .get("/api/pat/CBAXABC/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNotFound() {
        given()
            .when()
            .get("/api/pat/CBADEF/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
            .when()
            .get("/api/pat/XYZXYZ/ABC")
            .then()
            .statusCode(200);
    }
}