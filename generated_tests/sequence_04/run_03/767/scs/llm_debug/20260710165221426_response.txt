package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    static {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCookieUserid() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user1234")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testCookieSession() {
        given()
            .pathParam("name", "session")
            .pathParam("val", "am")
            .pathParam("site", "abc.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testCookieOther() {
        given()
            .pathParam("name", "other")
            .pathParam("val", "val")
            .pathParam("site", "site")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}