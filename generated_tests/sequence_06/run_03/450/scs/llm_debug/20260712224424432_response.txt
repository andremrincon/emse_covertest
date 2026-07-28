package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class CookieTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testCookieUserid() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/cookie/userid/user12345/example.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCookieSessionValid() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/cookie/session/am/abc.com")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCookieSessionInvalid() {
        RestAssured.given()
                .when()
                .get(baseUrl + "/api/cookie/session/pm/abc.com")
                .then()
                .statusCode(200);
    }
}