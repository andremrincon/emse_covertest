package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CookieTest {

    @Test(timeout = 60000)
    public void testUseridValid() {
        given()
            .when()
                .get("/api/cookie/userid/user1234/example.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridShortVal() {
        given()
            .when()
                .get("/api/cookie/userid/user12/example.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridInvalidPrefix() {
        given()
            .when()
                .get("/api/cookie/userid/admin123/example.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionValid() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionInvalidVal() {
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
                .get("/api/cookie/other/am/abc.com")
            .then()
                .statusCode(200);
    }
}