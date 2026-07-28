package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CookieTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testUserIdWithUserPrefixLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/user12345/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdWithNonUserPrefixLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/abc12345/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/abc/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndOtherSite() {
        given()
            .when()
                .get("/api/cookie/session/am/other.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .when()
                .get("/api/cookie/other/anything/anything.com")
            .then()
                .statusCode(200);
    }
}