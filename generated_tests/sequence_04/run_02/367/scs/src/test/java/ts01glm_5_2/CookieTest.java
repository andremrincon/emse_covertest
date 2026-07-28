package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("host", "localhost");
        String port = System.getProperty("port", "8080");
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testUserIdWithUserPrefixAndLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/user12345/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithLongValueButNoUserPrefix() {
        given()
            .when()
                .get("/api/cookie/userid/abcdefg/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/abc/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmValAndAbcSite() {
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
                .get("/api/cookie/session/xyz/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNonUserIdNonSessionName() {
        given()
            .when()
                .get("/api/cookie/other/someval/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}