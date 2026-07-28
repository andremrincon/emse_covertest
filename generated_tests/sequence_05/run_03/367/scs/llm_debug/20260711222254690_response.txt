package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testUseridValidUser() {
        given()
        .when()
            .get(baseUrl + "/api/cookie/userid/user1234/site.com")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShortVal() {
        given()
        .when()
            .get(baseUrl + "/api/cookie/userid/user1/site.com")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridInvalidPrefix() {
        given()
        .when()
            .get(baseUrl + "/api/cookie/userid/admin123/site.com")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionValid() {
        given()
        .when()
            .get(baseUrl + "/api/cookie/session/am/abc.com")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionInvalidVal() {
        given()
        .when()
            .get(baseUrl + "/api/cookie/session/pm/abc.com")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
        .when()
            .get(baseUrl + "/api/cookie/other/val/site.com")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}