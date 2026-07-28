package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
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
    public void testUserIdWithUserPrefixAndLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/username/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithLongValueNotUserPrefix() {
        given()
            .when()
                .get("/api/cookie/userid/admin123/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/user/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmValAndAbcComSite() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionWithNonMatchingValOrSite() {
        given()
            .when()
                .get("/api/cookie/session/am/example.com")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNonUserIdNonSessionName() {
        given()
            .when()
                .get("/api/cookie/session-id/abc-123-xyz-789/example.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}