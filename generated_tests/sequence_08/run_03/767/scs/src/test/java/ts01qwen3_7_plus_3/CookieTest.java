package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class CookieTest {

    @Before
    public void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUseridValidUser() {
        RestAssured.given()
            .when()
            .get("/api/cookie/userid/user1234/example.com")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridShortVal() {
        RestAssured.given()
            .when()
            .get("/api/cookie/userid/user12/example.com")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridInvalidPrefix() {
        RestAssured.given()
            .when()
            .get("/api/cookie/userid/admin123/example.com")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionValid() {
        RestAssured.given()
            .when()
            .get("/api/cookie/session/am/abc.com")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionInvalid() {
        RestAssured.given()
            .when()
            .get("/api/cookie/session/pm/abc.com")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        RestAssured.given()
            .when()
            .get("/api/cookie/other/val/site.com")
            .then()
            .statusCode(200);
    }
}