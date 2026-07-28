package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCookieUserIdValid() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user1234")
            .pathParam("site", "example.com")
        .when()
            .get(getBaseUrl() + "/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testCookieUserIdInvalidLength() {
        given()
            .pathParam("name", "userid")
            .pathParam("val", "user12")
            .pathParam("site", "example.com")
        .when()
            .get(getBaseUrl() + "/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testCookieSessionValid() {
        given()
            .pathParam("name", "session")
            .pathParam("val", "am")
            .pathParam("site", "abc.com")
        .when()
            .get(getBaseUrl() + "/api/cookie/{name}/{val}/{site}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }
}