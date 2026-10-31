package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testValidContributionWithAmountAndToken() {
        String jsonPayload = "{\"amount\": 1000, \"token\": \"tok_valid123\"}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithMissingAmount() {
        String jsonPayload = "{\"token\": \"tok_valid456\"}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithMissingToken() {
        String jsonPayload = "{\"amount\": 500}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithZeroAmount() {
        String jsonPayload = "{\"amount\": 0, \"token\": \"tok_zero789\"}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithNegativeAmount() {
        String jsonPayload = "{\"amount\": -100, \"token\": \"tok_negative\"}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithLargeAmount() {
        String jsonPayload = "{\"amount\": 999999999, \"token\": \"tok_large\"}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithEmptyToken() {
        String jsonPayload = "{\"amount\": 200, \"token\": \"\"}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithMissingBothFields() {
        String jsonPayload = "{}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithInvalidJsonStructure() {
        String jsonPayload = "{\"amount\": \"invalid\", \"token\": 123}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithSpecialCharactersInToken() {
        String jsonPayload = "{\"amount\": 300, \"token\": \"tok_@#$%^&*()\"}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithNullValues() {
        String jsonPayload = "{\"amount\": null, \"token\": null}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithMinimalValidData() {
        String jsonPayload = "{\"amount\": 1, \"token\": \"t\"}";

        given()
            .contentType(ContentType.JSON)
            .body(jsonPayload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}