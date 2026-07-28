package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class CookieTest {

    @Test(timeout = 60000)
    public void testUseridValidUser() {
        RestAssured.given()
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
        RestAssured.given()
                .pathParam("name", "userid")
                .pathParam("val", "user1")
                .pathParam("site", "example.com")
                .when()
                .get("http://localhost:8080/api/cookie/{name}/{val}/{site}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridInvalidPrefix() {
        RestAssured.given()
                .pathParam("name", "userid")
                .pathParam("val", "admin12345")
                .pathParam("site", "example.com")
                .when()
                .get("http://localhost:8080/api/cookie/{name}/{val}/{site}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionValidAm() {
        RestAssured.given()
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
        RestAssured.given()
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
        RestAssured.given()
                .pathParam("name", "other")
                .pathParam("val", "any")
                .pathParam("site", "any")
                .when()
                .get("http://localhost:8080/api/cookie/{name}/{val}/{site}")
                .then()
                .statusCode(200);
    }
}