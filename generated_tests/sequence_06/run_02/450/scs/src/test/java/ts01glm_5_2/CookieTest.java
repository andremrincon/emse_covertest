package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CookieTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUserIdCookieWithUserPrefixAndLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/user12345/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdCookieWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/user/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testSessionCookieWithAmValueAndAbcSite() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("1"));
    }

    @Test(timeout = 60000)
    public void testSessionCookieWithAmValueAndDifferentSite() {
        given()
            .when()
                .get("/api/cookie/session/am/example.com")
            .then()
                .statusCode(200)
                .body(containsString("2"));
    }

    @Test(timeout = 60000)
    public void testSessionCookieWithNonAmValue() {
        given()
            .when()
                .get("/api/cookie/session/xyz/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("2"));
    }

    @Test(timeout = 60000)
    public void testCookieWithUnknownName() {
        given()
            .when()
                .get("/api/cookie/unknown/somevalue/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }
}