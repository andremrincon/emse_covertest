package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testUserIdCookieWithUserPrefixLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/user1234/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdCookieWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/abc/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUserIdCookieWithLongValueNotUserPrefix() {
        given()
            .when()
                .get("/api/cookie/userid/abcdefg/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionCookieMatchingValAndSite() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionCookieNonMatchingVal() {
        given()
            .when()
                .get("/api/cookie/session/xyz/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherCookieName() {
        given()
            .when()
                .get("/api/cookie/other/val/site.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}