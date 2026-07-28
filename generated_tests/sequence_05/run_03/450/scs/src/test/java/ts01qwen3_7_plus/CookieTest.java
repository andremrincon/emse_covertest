package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CookieTest {

    @Test(timeout = 60000)
    public void testUseridValidUser() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user12345")
            .pathParam("site", "example.com")
        .when()
            .get("http://localhost:8080/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridShortVal() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user123")
            .pathParam("site", "example.com")
        .when()
            .get("http://localhost:8080/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridInvalidPrefix() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "admin12345")
            .pathParam("site", "example.com")
        .when()
            .get("http://localhost:8080/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionValid() {
        given()
            .pathParam("name", "session")
            .pathParam("val", "am")
            .pathParam("site", "abc.com")
        .when()
            .get("http://localhost:8080/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionInvalidVal() {
        given()
            .pathParam("name", "session")
            .pathParam("val", "pm")
            .pathParam("site", "abc.com")
        .when()
            .get("http://localhost:8080/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .pathParam("name", "other")
            .pathParam("val", "val")
            .pathParam("site", "site.com")
        .when()
            .get("http://localhost:8080/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }
}