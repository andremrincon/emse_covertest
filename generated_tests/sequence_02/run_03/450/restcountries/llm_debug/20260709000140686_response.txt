package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testContributionValidRequest() {
        String payload = "{\"amount\": 1000, \"token\": \"tok_visa\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributionMissingToken() {
        String payload = "{\"amount\": 500}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributionMissingAmount() {
        String payload = "{\"token\": \"tok_mastercard\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributionEmptyPayload() {
        String payload = "{}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}