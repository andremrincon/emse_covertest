package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @Test(timeout = 60000)
    public void testCookieUserid() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;

        given()
            .when()
            .get("/api/cookie/userid/user1234/anysite")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/cookie/userid/user1234/anysite")
            .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testCookieSession() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;

        given()
            .when()
            .get("/api/cookie/session/am/abc.com")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/cookie/session/am/abc.com")
            .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testCookieOther() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;

        given()
            .when()
            .get("/api/cookie/other/val/site")
            .then()
            .statusCode(lessThan(300));

        given()
            .when()
            .get("/api/cookie/other/val/site")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}