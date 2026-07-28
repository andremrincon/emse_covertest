package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CookieTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testUseridWithLongValStartingWithUser() {
        given()
            .when()
                .get("/api/cookie/userid/user123/example.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithShortVal() {
        given()
            .when()
                .get("/api/cookie/userid/user1/example.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithLongValNotStartingWithUser() {
        given()
            .when()
                .get("/api/cookie/userid/admin123/example.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndOtherSite() {
        given()
            .when()
                .get("/api/cookie/session/am/other.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithPmAndAbcCom() {
        given()
            .when()
                .get("/api/cookie/session/pm/abc.com")
            .then()
                .statusCode(200);
    }
}