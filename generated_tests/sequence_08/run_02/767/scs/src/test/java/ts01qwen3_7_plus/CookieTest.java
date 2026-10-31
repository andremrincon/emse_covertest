package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUseridValLongStartsWithUser() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user12345")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridValShort() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user12")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridValLongNotStartsWithUser() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "admin12345")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmAbcCom() {
        given()
            .pathParam("name", "session")
            .pathParam("val", "am")
            .pathParam("site", "abc.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionPmAbcCom() {
        given()
            .pathParam("name", "session")
            .pathParam("val", "pm")
            .pathParam("site", "abc.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSessionAmXyzCom() {
        given()
            .pathParam("name", "session")
            .pathParam("val", "am")
            .pathParam("site", "xyz.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .body(equalTo("2"));
    }
}