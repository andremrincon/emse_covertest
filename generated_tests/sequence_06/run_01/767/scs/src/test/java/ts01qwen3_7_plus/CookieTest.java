package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @Test(timeout = 60000)
    public void testUseridWithValidUserPrefix() {
        given()
            .when()
            .get("/api/cookie/userid/user1234/any.com")
            .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValue() {
        given()
            .when()
            .get("/api/cookie/userid/user1/any.com")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridWithNonUserPrefix() {
        given()
            .when()
            .get("/api/cookie/userid/admin123/any.com")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        given()
            .when()
            .get("/api/cookie/session/am/abc.com")
            .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionWithOtherValues() {
        given()
            .when()
            .get("/api/cookie/session/pm/abc.com")
            .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .when()
            .get("/api/cookie/other/any/any.com")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}