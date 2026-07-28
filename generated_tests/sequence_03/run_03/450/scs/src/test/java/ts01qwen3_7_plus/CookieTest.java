package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CookieTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUseridWithLongValStartingWithUser() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user1234")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithLongValNotStartingWithUser() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "admin1234")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithShortVal() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user1")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
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
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithPmAndAbcCom() {
        given()
            .pathParam("name", "session")
            .pathParam("val", "pm")
            .pathParam("site", "abc.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .pathParam("name", "other")
            .pathParam("val", "value")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }
}