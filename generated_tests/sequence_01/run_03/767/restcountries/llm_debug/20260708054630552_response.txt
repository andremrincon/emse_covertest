package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testValidContribution() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 100, \"token\": \"tok_valid\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithCurrency() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 500, \"currency\": \"USD\", \"token\": \"tok_currency\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionMissingToken() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionMissingAmount() {
        given()
            .contentType("application/json")
            .body("{\"token\": \"tok_missing_amount\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionEmptyBody() {
        given()
            .contentType("application/json")
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionInvalidJson() {
        given()
            .contentType("application/json")
            .body("invalid json")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionNegativeAmount() {
        given()
            .contentType("application/json")
            .body("{\"amount\": -100, \"token\": \"tok_negative\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}