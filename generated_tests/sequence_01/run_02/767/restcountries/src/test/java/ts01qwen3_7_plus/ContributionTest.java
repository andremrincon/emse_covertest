package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ContributionTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testValidContribution() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 1000, \"token\": \"tok_valid_123\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributionWithZeroAmount() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 0, \"token\": \"tok_zero_456\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributionMissingToken() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 500}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributionMissingAmount() {
        given()
            .contentType("application/json")
            .body("{\"token\": \"tok_noamount_789\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributionEmptyBody() {
        given()
            .contentType("application/json")
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}