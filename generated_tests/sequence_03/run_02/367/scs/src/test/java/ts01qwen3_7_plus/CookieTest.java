package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUseridValidUser() {
        RestAssured.given()
                .when()
                .get("/api/cookie/userid/user1234/site1")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridShortVal() {
        RestAssured.given()
                .when()
                .get("/api/cookie/userid/user1/site1")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridInvalidUser() {
        RestAssured.given()
                .when()
                .get("/api/cookie/userid/admin1234/site1")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionValid() {
        RestAssured.given()
                .when()
                .get("/api/cookie/session/am/abc.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionInvalidVal() {
        RestAssured.given()
                .when()
                .get("/api/cookie/session/pm/abc.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherName() {
        RestAssured.given()
                .when()
                .get("/api/cookie/other/val/site")
                .then()
                .statusCode(200);
    }
}