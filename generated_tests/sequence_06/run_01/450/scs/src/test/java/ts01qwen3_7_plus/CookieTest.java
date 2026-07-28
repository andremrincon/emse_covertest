package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testUseridValidUser() {
        String name = "userid";
        String val = "user1234";
        String site = "example.com";

        given()
            .pathParam("name", name)
            .pathParam("val", val)
            .pathParam("site", site)
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridShortVal() {
        String name = "userid";
        String val = "user1";
        String site = "example.com";

        given()
            .pathParam("name", name)
            .pathParam("val", val)
            .pathParam("site", site)
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridInvalidPrefix() {
        String name = "userid";
        String val = "admin123";
        String site = "example.com";

        given()
            .pathParam("name", name)
            .pathParam("val", val)
            .pathParam("site", site)
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionValid() {
        String name = "session";
        String val = "am";
        String site = "abc.com";

        given()
            .pathParam("name", name)
            .pathParam("val", val)
            .pathParam("site", site)
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionInvalid() {
        String name = "session";
        String val = "pm";
        String site = "abc.com";

        given()
            .pathParam("name", name)
            .pathParam("val", val)
            .pathParam("site", site)
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        String name = "other";
        String val = "val";
        String site = "site.com";

        given()
            .pathParam("name", name)
            .pathParam("val", val)
            .pathParam("site", site)
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }
}