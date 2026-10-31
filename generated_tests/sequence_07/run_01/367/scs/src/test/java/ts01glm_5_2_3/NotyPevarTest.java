package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testSubjectWhenXPlusYEquals56() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWhenHello7MatchAndCompareLessThanZero() {
        given()
            .when()
                .get("/api/notypevar/7/zzzz")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWhenAllConditionsFalse() {
        given()
            .when()
                .get("/api/notypevar/3/a")
            .then()
                .statusCode(200);
    }
}