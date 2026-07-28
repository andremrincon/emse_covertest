package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CookieTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testUseridValidUser() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/userid/user1234/any.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridShortVal() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/userid/user12/any.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridInvalidUser() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/userid/admin123/any.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionValid() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/session/am/abc.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionInvalid() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/session/pm/abc.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/other/val/site.com")
        .then()
            .statusCode(200);
    }
}