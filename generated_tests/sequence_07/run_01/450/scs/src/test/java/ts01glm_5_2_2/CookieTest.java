package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUserIdNameWithLongValStartingWithUser() {
        given()
                .when()
                .get("/api/cookie/userid/user12345/abc.com")
                .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdNameWithLongValNotStartingWithUser() {
        given()
                .when()
                .get("/api/cookie/userid/test1234/abc.com")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUserIdNameWithShortVal() {
        given()
                .when()
                .get("/api/cookie/userid/abc/abc.com")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionNameWithMatchingValAndSite() {
        given()
                .when()
                .get("/api/cookie/session/am/abc.com")
                .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNameWithNonMatchingSite() {
        given()
                .when()
                .get("/api/cookie/session/am/xyz.com")
                .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNonUserIdNonSessionName() {
        given()
                .when()
                .get("/api/cookie/other/someval/somesite.com")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}