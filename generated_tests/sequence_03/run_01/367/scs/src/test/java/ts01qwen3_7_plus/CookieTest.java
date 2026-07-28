package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testUseridWithValidUserPrefix() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user12345")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridWithInvalidPrefix() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "admin12345")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValue() {
        given()
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
    public void testSessionWithAmAndAbcCom() {
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
    public void testSessionWithInvalidVal() {
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
    public void testOtherName() {
        given()
            .pathParam("name", "other")
            .pathParam("val", "anything")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}