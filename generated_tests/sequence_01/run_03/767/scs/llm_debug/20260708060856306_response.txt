package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUseridWithValidUserPrefix() {
        RestAssured.given()
                .when()
                .get("/api/cookie/userid/user123/example.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithShortValue() {
        RestAssured.given()
                .when()
                .get("/api/cookie/userid/user1/example.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUseridWithInvalidPrefix() {
        RestAssured.given()
                .when()
                .get("/api/cookie/userid/admin123/example.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithValidAmAndSite() {
        RestAssured.given()
                .when()
                .get("/api/cookie/session/am/abc.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithInvalidVal() {
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
                .get("/api/cookie/other/any/any")
                .then()
                .statusCode(200);
    }
}