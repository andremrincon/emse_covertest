package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CookieTest {

    @Test(timeout = 60000)
    public void testUseridWithValidUserPrefix() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/cookie/userid/user1234/any.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithInvalidUserPrefix() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/cookie/userid/admin1234/any.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValue() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/cookie/userid/user1/any.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/cookie/session/am/abc.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithPmAndAbcCom() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/cookie/session/pm/abc.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/cookie/other/val/site.com")
        .then()
            .statusCode(200);
    }
}