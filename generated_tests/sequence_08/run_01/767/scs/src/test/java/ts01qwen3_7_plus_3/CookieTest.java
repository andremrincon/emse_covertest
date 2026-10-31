package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Test;

public class CookieTest {

    @Test(timeout = 60000)
    public void testUseridLongUser() {
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
                .get("/api/cookie/userid/user1/example.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridLongNotUser() {
        RestAssured.given()
                .when()
                .get("/api/cookie/userid/admin1234/example.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionAmAbc() {
        RestAssured.given()
                .when()
                .get("/api/cookie/session/am/abc.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionNotAm() {
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
                .get("/api/cookie/other/val/example.com")
                .then()
                .statusCode(200);
    }
}