package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class PatTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatTooShortReturnsZero() {
        given()
            .when()
                .get("/api/pat/abcde/ab")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverseReturnsOne() {
        given()
            .when()
                .get("/api/pat/abcdefg/abc")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPatReturnsTwo() {
        given()
            .when()
                .get("/api/pat/gfedcba/abc")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPalindromePatFollowedByReverseReturnsIndex() {
        given()
            .when()
                .get("/api/pat/abccba/abc")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseLaterReturnsIndex() {
        given()
            .when()
                .get("/api/pat/abcXcba/abc")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFirstPatAdjacentReturnsIndex() {
        given()
            .when()
                .get("/api/pat/cbaabc/abc")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFirstPatLaterReturnsIndex() {
        given()
            .when()
                .get("/api/pat/cbaXabc/abc")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFoundReturnsZero() {
        given()
            .when()
                .get("/api/pat/xyz123/abc")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatSingleArgEndpoint() {
        given()
            .when()
                .get("/api/pat/hello")
            .then()
                .statusCode(lessThan(300));
    }
}