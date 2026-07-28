package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    private static final String BASE_URL = "http://localhost:8080";

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testUseridWithValidUserPrefix() {
        given()
            .when()
            .get("/api/cookie/userid/user123/example.com")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/api/cookie/userid/user123/example.com");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValue() {
        given()
            .when()
            .get("/api/cookie/userid/user1/example.com")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/api/cookie/userid/user1/example.com");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithInvalidPrefix() {
        given()
            .when()
            .get("/api/cookie/userid/admin123/example.com")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/api/cookie/userid/admin123/example.com");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithValidCredentials() {
        given()
            .when()
            .get("/api/cookie/session/am/abc.com")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/api/cookie/session/am/abc.com");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithInvalidValue() {
        given()
            .when()
            .get("/api/cookie/session/pm/abc.com")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/api/cookie/session/pm/abc.com");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownCookieName() {
        given()
            .when()
            .get("/api/cookie/other/any/any")
            .then()
            .statusCode(lessThan(300));

        Response response = given()
            .when()
            .get("/api/cookie/other/any/any");

        response.then().statusCode(200);
    }
}