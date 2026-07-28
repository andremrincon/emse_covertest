package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CookieTest {

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
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testUserIdWithUserPrefixAndLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/usertest/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithLongValueNotStartingWithUser() {
        given()
            .when()
                .get("/api/cookie/userid/abcdefg/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/abc/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmValAndAbcSite() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("1"));
    }

    @Test(timeout = 60000)
    public void testSessionWithNonMatchingValOrSite() {
        given()
            .when()
                .get("/api/cookie/session/xyz/example.com")
            .then()
                .statusCode(200)
                .body(containsString("2"));
    }

    @Test(timeout = 60000)
    public void testNonUserIdNonSessionName() {
        given()
            .when()
                .get("/api/cookie/other/someval/example.com")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }
}