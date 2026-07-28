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
    public void testUserIdWithValidUserPrefixLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/user1234/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
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
    public void testUserIdWithWrongPrefix() {
        given()
            .when()
                .get("/api/cookie/userid/admin123/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndWrongSite() {
        given()
            .when()
                .get("/api/cookie/session/am/other.com")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNeitherUserIdNorSessionName() {
        given()
            .when()
                .get("/api/cookie/other/xyz/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}