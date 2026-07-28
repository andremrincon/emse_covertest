package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CookieTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUserIdWithLongValueStartingWithUser() {
        given()
            .when()
                .get("/api/cookie/userid/user123/test.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/user/test.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdWithLongValueNotStartingWithUser() {
        given()
            .when()
                .get("/api/cookie/userid/test123/test.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithMatchingValueAndSite() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithNonMatchingValue() {
        given()
            .when()
                .get("/api/cookie/session/pm/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonUserIdNonSessionName() {
        given()
            .when()
                .get("/api/cookie/other/test/test.com")
            .then()
                .statusCode(200);
    }
}