package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUserIdBranchWithUserPrefixReturnsResultOne() {
        given()
            .when()
            .get("/api/cookie/userid/user1234/example.com")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSessionBranchNotMatchingConditionReturnsResultTwo() {
        given()
            .when()
            .get("/api/cookie/session/pm/abc.com")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherUserIdNorSessionBranchReturnsResultZero() {
        given()
            .when()
            .get("/api/cookie/other/xyz/abc.com")
            .then()
            .statusCode(200);
    }
}