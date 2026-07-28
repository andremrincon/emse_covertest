package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CookieTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUseridWithValidUserPrefix() {
        given()
            .when()
            .get("/api/cookie/userid/user1234/any-site")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithInvalidUserPrefix() {
        given()
            .when()
            .get("/api/cookie/userid/admin123/any-site")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValue() {
        given()
            .when()
            .get("/api/cookie/userid/user1/any-site")
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
    public void testSessionWithInvalidValOrSite() {
        given()
            .when()
            .get("/api/cookie/session/pm/abc.com")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .when()
            .get("/api/cookie/other/anything/anything")
            .then()
            .statusCode(200);
    }
}