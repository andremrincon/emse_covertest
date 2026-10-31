package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CookieTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUserIdBranchWithUserPrefixResultOne() {
        given()
            .when()
                .get("/api/cookie/userid/user1234567/example.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionBranchWithMatchingValAndSiteResultOne() {
        given()
            .when()
                .get("/api/cookie/session/am/abc.com")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionBranchWithNonMatchingValResultTwo() {
        given()
            .when()
                .get("/api/cookie/session/pm/xyz.com")
            .then()
                .statusCode(200);
    }
}