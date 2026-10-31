package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUseridWithValidUserPrefix() {
        RestAssured.given()
                .pathParam("name", "userid")
                .pathParam("val", "user1234")
                .pathParam("site", "example.com")
                .when()
                .get("/api/cookie/{name}/{val}/{site}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithShortVal() {
        RestAssured.given()
                .pathParam("name", "userid")
                .pathParam("val", "user12")
                .pathParam("site", "example.com")
                .when()
                .get("/api/cookie/{name}/{val}/{site}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithInvalidPrefix() {
        RestAssured.given()
                .pathParam("name", "userid")
                .pathParam("val", "admin1234")
                .pathParam("site", "example.com")
                .when()
                .get("/api/cookie/{name}/{val}/{site}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        RestAssured.given()
                .pathParam("name", "session")
                .pathParam("val", "am")
                .pathParam("site", "abc.com")
                .when()
                .get("/api/cookie/{name}/{val}/{site}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithPmAndXyzCom() {
        RestAssured.given()
                .pathParam("name", "session")
                .pathParam("val", "pm")
                .pathParam("site", "xyz.com")
                .when()
                .get("/api/cookie/{name}/{val}/{site}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        RestAssured.given()
                .pathParam("name", "other")
                .pathParam("val", "val")
                .pathParam("site", "site")
                .when()
                .get("/api/cookie/{name}/{val}/{site}")
                .then()
                .statusCode(200);
    }
}