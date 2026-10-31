package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @Before
    public void setUp() {
        String host = System.getProperty("server.host");
        if (host == null) {
            host = System.getenv("SERVER_HOST");
        }
        if (host != null) {
            RestAssured.baseURI = "http://" + host;
        } else {
            RestAssured.baseURI = "http://localhost";
        }
        String port = System.getProperty("server.port");
        if (port == null) {
            port = System.getenv("SERVER_PORT");
        }
        if (port != null) {
            RestAssured.port = Integer.parseInt(port);
        } else {
            RestAssured.port = 8080;
        }
    }

    @Test(timeout = 60000)
    public void testUserIdWithUserPrefixLongValueReturns1() {
        given()
            .when()
                .get("/api/cookie/userid/username123/example.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdLongValueNotUserPrefixReturns0() {
        given()
            .when()
                .get("/api/cookie/userid/admin1234/example.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUserIdShortValueReturns0() {
        given()
            .when()
                .get("/api/cookie/userid/usr/example.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmAbcComReturns1() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingValueReturns2() {
        given()
            .when()
                .get("/api/cookie/session/pm/abc.com")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNeitherUserIdNorSessionReturns0() {
        given()
            .when()
                .get("/api/cookie/othertype/somevalue/anything.com")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}