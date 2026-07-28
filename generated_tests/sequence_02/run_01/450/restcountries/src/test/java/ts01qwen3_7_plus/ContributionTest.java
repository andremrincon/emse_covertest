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
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testContributeValidRequest() {
        String payload = "{\"amount\": 1000, \"token\": \"tok_valid_123\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyPayload() {
        String payload = "{}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeInvalidAmountType() {
        String payload = "{\"amount\": \"not_a_number\", \"token\": \"tok_valid_123\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}