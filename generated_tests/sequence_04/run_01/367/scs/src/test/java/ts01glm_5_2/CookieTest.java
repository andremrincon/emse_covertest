package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CookieTest {

    @BeforeClass
    public static void setUp() {
        String baseURI = System.getProperty("baseURI");
        if (baseURI == null) {
            baseURI = "http://localhost:8080";
        }
        RestAssured.baseURI = baseURI;
    }

    @Test(timeout = 60000)
    public void testUserIdWithUserPrefixLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/user123/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdWithNonUserPrefixLongValue() {
        given()
            .when()
                .get("/api/cookie/userid/admin12/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUserIdWithShortValue() {
        given()
            .when()
                .get("/api/cookie/userid/user/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndAbcCom() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithNonAmValue() {
        given()
            .when()
                .get("/api/cookie/session/pm/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionWithAmAndNonAbcComSite() {
        given()
            .when()
                .get("/api/cookie/session/am/xyz.com")
            .then()
                .statusCode(200);
    }
}