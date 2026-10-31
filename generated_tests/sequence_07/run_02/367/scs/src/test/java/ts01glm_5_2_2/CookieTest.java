package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CookieTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUserIdLongValStartsWithUser() {
        given()
        .when()
            .get("/api/cookie/userid/userabc/example.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdLongValNotStartingWithUser() {
        given()
        .when()
            .get("/api/cookie/userid/abcdefg/example.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdShortVal() {
        given()
        .when()
            .get("/api/cookie/userid/abc/example.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionAmAbcCom() {
        given()
        .when()
            .get("/api/cookie/session/am/abc.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionNotAm() {
        given()
        .when()
            .get("/api/cookie/session/xyz/abc.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherUserIdNorSession() {
        given()
        .when()
            .get("/api/cookie/othername/xyz/example.com")
        .then()
            .statusCode(200);
    }
}