package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testSubject_XPlusYEquals56() {
        given()
            .get(baseUrl + "/api/notypevar/28/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_XsPlusYEqualsHello7() {
        given()
            .get(baseUrl + "/api/notypevar/7/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_XsCompareToSLessThan0() {
        given()
            .get(baseUrl + "/api/notypevar/1/world")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_AllConditionsFalse() {
        given()
            .get(baseUrl + "/api/notypevar/1/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_InvalidInteger() {
        given()
            .get(baseUrl + "/api/notypevar/abc/a")
        .then()
            .statusCode(400);
    }
}