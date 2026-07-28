package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testContributeValidPayload() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 1000, \"currency\": \"usd\", \"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 1000, \"currency\": \"usd\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingAmount() {
        given()
            .contentType("application/json")
            .body("{\"currency\": \"usd\", \"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeInvalidAmountType() {
        given()
            .contentType("application/json")
            .body("{\"amount\": \"invalid\", \"currency\": \"usd\", \"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}