package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class CookieTest {

    @Test(timeout = 60000)
    public void testUseridValidLong() {
        RestAssured.given()
                .when()
                .get("/api/cookie/userid/user1234/example.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridShort() {
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
    public void testSessionInvalidVal() {
        RestAssured.given()
                .when()
                .get("/api/cookie/session/pm/abc.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionInvalidSite() {
        RestAssured.given()
                .when()
                .get("/api/cookie/session/am/xyz.com")
                .then()
                .statusCode(200);
    }
}