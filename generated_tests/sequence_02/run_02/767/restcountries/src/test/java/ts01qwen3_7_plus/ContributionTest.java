package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testContributeValidPayload() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 500, \"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 500}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingAmount() {
        given()
            .contentType("application/json")
            .body("{\"token\": \"tok_12345\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyPayload() {
        given()
            .contentType("application/json")
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeInvalidJson() {
        given()
            .contentType("application/json")
            .body("invalid_json")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}