package ts01qwen3_7_plus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Test;

public class CookieTest {

    @Test(timeout = 60000)
    public void testCookieUseridWithValidUserValue() {
        given()
            .when()
                .get("/api/cookie/userid/user1234/any.com")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/api/cookie/userid/user1234/any.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testCookieUseridWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/user1/any.com")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/api/cookie/userid/user1/any.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testCookieUseridWithNonUserPrefix() {
        given()
            .when()
                .get("/api/cookie/userid/admin123/any.com")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/api/cookie/userid/admin123/any.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testCookieSessionWithAmAndAbcCom() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testCookieSessionWithAmAndOtherSite() {
        given()
            .when()
                .get("/api/cookie/session/am/other.com")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/api/cookie/session/am/other.com")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testCookieSessionWithOtherValAndAbcCom() {
        given()
            .when()
                .get("/api/cookie/session/pm/abc.com")
            .then()
                .statusCode(lessThan(300));

        given()
            .when()
                .get("/api/cookie/session/pm/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }
}