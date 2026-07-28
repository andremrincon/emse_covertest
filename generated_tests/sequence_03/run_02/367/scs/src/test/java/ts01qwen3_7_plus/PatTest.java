package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @Before
    public void setUp() {
        io.restassured.RestAssured.baseURI = System.getProperty("base.uri", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testConstructorCoverage() {
        given()
            .when()
                .get("/api/pat/test/abc")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testSubjectLine63_PalindromePatRevPat() {
        given()
            .when()
                .get("/api/pat/abccba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLines84_85_PalindromeRevPatPat() {
        given()
            .when()
                .get("/api/pat/cbaabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLine89_NonAdjacentRevPatPat() {
        given()
            .when()
                .get("/api/pat/cba-xyz-abc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectPatNotFound() {
        given()
            .when()
                .get("/api/pat/hello/xyz")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectPatFoundOnly() {
        given()
            .when()
                .get("/api/pat/helloworld/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectRevPatFoundOnly() {
        given()
            .when()
                .get("/api/pat/hellodlrow/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNonAdjacentPatRevPat() {
        given()
            .when()
                .get("/api/pat/abc-xyz-cba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseShortStringAttempt() {
        given()
            .when()
                .get("/api/pat/a")
            .then()
                .statusCode(lessThan(300));
    }
}