package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    private static final String BASE_URL = System.getProperty("test.base.url", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testUseridWithValidUserPrefix() {
        given()
            .when()
                .get(BASE_URL + "/api/cookie/userid/user1234/any.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridWithShortVal() {
        given()
            .when()
                .get(BASE_URL + "/api/cookie/userid/user12/any.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridWithNonUserPrefix() {
        given()
            .when()
                .get(BASE_URL + "/api/cookie/userid/admin123/any.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        given()
            .when()
                .get(BASE_URL + "/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndOtherSite() {
        given()
            .when()
                .get(BASE_URL + "/api/cookie/session/am/other.com")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .when()
                .get(BASE_URL + "/api/cookie/other/any/any.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}