package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUserIdValidUserPrefixLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/user12345/example.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdLongValueInvalidPrefix() {
        given()
            .when()
                .get("/api/cookie/userid/admin12/example.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/abc/example.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionAmAbcCom() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionAmDifferentSite() {
        given()
            .when()
                .get("/api/cookie/session/am/other.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherUserIdNorSession() {
        given()
            .when()
                .get("/api/cookie/other/someval/site.com")
            .then()
                .statusCode(200);
    }
}