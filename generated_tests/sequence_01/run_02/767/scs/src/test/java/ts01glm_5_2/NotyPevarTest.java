package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevarConstructorAndBranchXPlusYEquals56() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranchHello7AndCompareToAndYGreaterThanX() {
        given()
            .when()
                .get("/api/notypevar/7/zzzz")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarInvalidIntegerReturns400() {
        given()
            .when()
                .get("/api/notypevar/abc/valid-string")
            .then()
                .statusCode(400);
    }
}