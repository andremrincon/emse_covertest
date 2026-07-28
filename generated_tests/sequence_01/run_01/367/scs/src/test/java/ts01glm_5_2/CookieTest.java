package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CookieTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCookieUseridBranchReturnsResultOne() {
        given()
            .when()
                .get("/api/cookie/userid/user12345/example.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCookieSessionBranchWithMatchingSiteReturnsResultOne() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCookieSessionBranchWithNonMatchingSiteReturnsResultTwo() {
        given()
            .when()
                .get("/api/cookie/session/xyz/other.com")
            .then()
                .statusCode(200);
    }
}