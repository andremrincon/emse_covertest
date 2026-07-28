package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testSubject_XPlusYEquals56() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/notypevar/28/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_XsPlusYEqualsHello7() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/notypevar/7/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_XsCompareToSLessThan0() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/notypevar/0/world")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_DefaultConstructorCoverage() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/notypevar/1/a")
        .then()
            .statusCode(200);
    }
}