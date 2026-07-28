package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CookieTest {

    @Test(timeout = 60000)
    public void testCookieUserId() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .baseUri(baseUrl)
            .pathParam("name", "userid")
            .pathParam("val", "user123")
            .pathParam("site", "example.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCookieSession() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .baseUri(baseUrl)
            .pathParam("name", "session")
            .pathParam("val", "am")
            .pathParam("site", "abc.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCookieOther() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        given()
            .baseUri(baseUrl)
            .pathParam("name", "other")
            .pathParam("val", "val")
            .pathParam("site", "site.com")
        .when()
            .get("/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200);
    }
}