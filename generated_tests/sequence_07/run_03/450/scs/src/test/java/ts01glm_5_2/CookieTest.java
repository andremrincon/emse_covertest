package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class CookieTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUserIdWithLongValStartingWithUser() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/userid/user12345/example.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortVal() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/userid/user/example.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdWithLongValNotStartingWithUser() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/userid/abcdefg/example.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/session/am/abc.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithNonMatchingVal() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/session/xyz/example.com")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonUserIdNonSessionName() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/cookie/other/abc/example.com")
        .then()
            .statusCode(200);
    }
}