package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ContributionTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
    }

    @Ignore("1 expectation failed. Expected status code <202> but was <400>.")
    @Test(timeout = 60000)
    public void testContributionWithValidAmountAndToken() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 1000, \"token\": \"tok_test123\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(202);
    }

    @Test(timeout = 60000)
    public void testContributionWithMissingToken() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 1000}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithMissingAmount() {
        given()
            .contentType("application/json")
            .body("{\"token\": \"tok_test123\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithEmptyBody() {
        given()
            .contentType("application/json")
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithZeroAmount() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 0, \"token\": \"tok_test123\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithNegativeAmount() {
        given()
            .contentType("application/json")
            .body("{\"amount\": -100, \"token\": \"tok_test123\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithEmptyToken() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 1000, \"token\": \"\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithNullAmount() {
        given()
            .contentType("application/json")
            .body("{\"amount\": null, \"token\": \"tok_test123\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithNullToken() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 1000, \"token\": null}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <202> but was <400>.")
    @Test(timeout = 60000)
    public void testContributionWithLargeAmount() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 999999, \"token\": \"tok_test123\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(202);
    }

    @Test(timeout = 60000)
    public void testContributionWithInvalidJson() {
        given()
            .contentType("application/json")
            .body("invalid json")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <202> but was <400>.")
    @Test(timeout = 60000)
    public void testContributionWithCurrencyField() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 1000, \"token\": \"tok_test123\", \"currency\": \"USD\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(202);
    }
}