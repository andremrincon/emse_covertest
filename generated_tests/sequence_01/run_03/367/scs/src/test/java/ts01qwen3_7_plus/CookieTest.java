package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testUseridWithValidUserPrefix() {
        given()
            .baseUri(BASE_URL)
            .pathParam("name", "userid")
            .pathParam("val", "user1234")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValue() {
        given()
            .baseUri(BASE_URL)
            .pathParam("name", "userid")
            .pathParam("val", "user1")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridWithInvalidPrefix() {
        given()
            .baseUri(BASE_URL)
            .pathParam("name", "userid")
            .pathParam("val", "admin1234")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionWithValidCredentials() {
        given()
            .baseUri(BASE_URL)
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
    public void testSessionWithInvalidCredentials() {
        given()
            .baseUri(BASE_URL)
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
    public void testUnknownName() {
        given()
            .baseUri(BASE_URL)
            .pathParam("name", "other")
            .pathParam("val", "any")
            .pathParam("site", "any")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}