package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLenLessThan3() {
        given()
            .when()
            .get("/api/pat/abc/ab")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundOnly() {
        given()
            .when()
            .get("/api/pat/abcde/abc")
            .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPalindromePatThenPatRev() {
        given()
            .when()
            .get("/api/pat/abccba/abc")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testBothFoundNotAdjacent() {
        given()
            .when()
            .get("/api/pat/abcxyzcba/abc")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundOnly() {
        given()
            .when()
            .get("/api/pat/edcbaxyz/abc")
            .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatCharMatchButNoPat() {
        given()
            .when()
            .get("/api/pat/edcbaaxyz/abc")
            .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPalindromePatRevThenPat() {
        given()
            .when()
            .get("/api/pat/cbaabc/abc")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testBothFoundNotAdjacentPatRevFirst() {
        given()
            .when()
            .get("/api/pat/cbaxyzabc/abc")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}