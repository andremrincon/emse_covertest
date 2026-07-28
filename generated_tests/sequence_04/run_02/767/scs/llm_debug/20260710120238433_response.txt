package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CookieTest {

    private static final String BASE_URL = System.getProperty("baseUrl", System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080");

    @Test(timeout = 60000)
    public void testUseridValidUser() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user123")
            .pathParam("site", "example.com")
        .when()
            .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridInvalidUser() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "admin123")
            .pathParam("site", "example.com")
        .when()
            .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridShortVal() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user1")
            .pathParam("site", "example.com")
        .when()
            .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
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
            .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionInvalid() {
        given()
            .pathParam("name", "session")
            .pathParam("val", "pm")
            .pathParam("site", "abc.com")
        .when()
            .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .pathParam("name", "other")
            .pathParam("val", "any")
            .pathParam("site", "example.com")
        .when()
            .get(BASE_URL + "/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }
}