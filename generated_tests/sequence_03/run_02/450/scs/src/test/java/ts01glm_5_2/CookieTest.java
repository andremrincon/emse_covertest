package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUserIdWithUserPrefixLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/user12345/example.com")
            .then()
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithNonUserPrefixLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/admin123/example.com")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/short/example.com")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionWithNonAmValue() {
        given()
            .when()
                .get("/api/cookie/session/pm/abc.com")
            .then()
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndNonAbcComSite() {
        given()
            .when()
                .get("/api/cookie/session/am/xyz.com")
            .then()
                .body(equalTo("2"));
    }
}