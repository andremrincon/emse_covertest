package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CookieTest {

    @Test(timeout = 60000)
    public void testUserIdBranch() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/cookie/userid/user12345/example.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionBranch() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/cookie/session/am/abc.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDefaultBranch() {
        given()
            .baseUri("http://localhost:8080")
        .when()
            .get("/api/cookie/other/val/site")
        .then()
            .statusCode(200);
    }
}