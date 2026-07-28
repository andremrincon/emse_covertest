package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class CookieTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUserIdWithUserPrefixAndLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/user123/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/user/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithLongValueNotStartingWithUser() {
        given()
            .when()
                .get("/api/cookie/userid/admin123/abc.com")
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
                .get("/api/cookie/session/xyz/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("2"));
    }

    @Test(timeout = 60000)
    public void testNonUserIdNonSessionName() {
        given()
            .when()
                .get("/api/cookie/other/somevalue/somesite.com")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }
}