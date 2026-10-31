package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUserIdNameWithUserPrefixLongValReturnsOne() {
        given()
                .when()
                .get("/api/cookie/userid/user123/abc.com")
                .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdNameWithNonUserPrefixLongValReturnsZero() {
        given()
                .when()
                .get("/api/cookie/userid/abc1234/abc.com")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUserIdNameWithShortValReturnsZero() {
        given()
                .when()
                .get("/api/cookie/userid/abc/abc.com")
                .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionNameWithAmValAndAbcSiteReturnsOne() {
        given()
                .when()
                .get("/api/cookie/session/am/abc.com")
                .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNameWithAmValAndDifferentSiteReturnsTwo() {
        given()
                .when()
                .get("/api/cookie/session/am/xyz.com")
                .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSessionNameWithNonAmValReturnsTwo() {
        given()
                .when()
                .get("/api/cookie/session/xyz/abc.com")
                .then()
                .statusCode(200)
                .body(equalTo("2"));
    }
}