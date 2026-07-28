package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUseridValidUser() {
        given()
        .when()
            .get(getBaseUrl() + "/api/cookie/userid/user12345/anysite")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridShortVal() {
        given()
        .when()
            .get(getBaseUrl() + "/api/cookie/userid/user12/anysite")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridInvalidPrefix() {
        given()
        .when()
            .get(getBaseUrl() + "/api/cookie/userid/admin12345/anysite")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionValid() {
        given()
        .when()
            .get(getBaseUrl() + "/api/cookie/session/am/abc.com")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionInvalid() {
        given()
        .when()
            .get(getBaseUrl() + "/api/cookie/session/pm/abc.com")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
        .when()
            .get(getBaseUrl() + "/api/cookie/other/val/site")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}