package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @Test(timeout = 60000)
    public void testCookieUseridValidUser() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user1234")
            .pathParam("site", "site1")
            .when()
                .get("/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testCookieUseridShortVal() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user12")
            .pathParam("site", "site1")
            .when()
                .get("/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testCookieUseridInvalidPrefix() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "admin123")
            .pathParam("site", "site1")
            .when()
                .get("/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testCookieSessionValid() {
        given()
            .pathParam("name", "session")
            .pathParam("val", "am")
            .pathParam("site", "abc.com")
            .when()
                .get("/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testCookieSessionInvalid() {
        given()
            .pathParam("name", "session")
            .pathParam("val", "pm")
            .pathParam("site", "abc.com")
            .when()
                .get("/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testCookieOtherName() {
        given()
            .pathParam("name", "other")
            .pathParam("val", "val")
            .pathParam("site", "site")
            .when()
                .get("/api/cookie/{name}/{val}/{site}")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}