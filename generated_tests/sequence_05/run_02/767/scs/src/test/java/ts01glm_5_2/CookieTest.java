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
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testUserIdWithUserPrefixLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/user1234/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/short/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithLongValueNotStartingWithUser() {
        given()
            .when()
                .get("/api/cookie/userid/test1234/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200)
                .body(containsString("1"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndDifferentSite() {
        given()
            .when()
                .get("/api/cookie/session/am/other.com")
            .then()
                .statusCode(200)
                .body(containsString("2"));
    }

    @Test(timeout = 60000)
    public void testNeitherUserIdNorSession() {
        given()
            .when()
                .get("/api/cookie/other/someval/somesite.com")
            .then()
                .statusCode(200)
                .body(containsString("0"));
    }
}