package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class PatTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatLengthLessThanThree() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abcdef/ab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverseFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abcdef/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundAdjacent() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abccba/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundNonAdjacent() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/abcxcba/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPatFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/cbaxyz/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatFoundAdjacent() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/cbaabc/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatFoundNonAdjacent() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/cbaxabc/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/xyzxyz/abc")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFirstCharMatchesButSubstringDoesNot() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/pat/axcbyc/abc")
        .then()
            .statusCode(200);
    }
}