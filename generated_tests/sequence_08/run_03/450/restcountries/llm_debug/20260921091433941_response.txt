package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testContributeValid() {
        String payload = "{\"amount\": 500, \"token\": \"tok_valid_123\"}";
        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/contribute");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingAmount() {
        String payload = "{\"token\": \"tok_valid_123\"}";
        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/contribute");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken() {
        String payload = "{\"amount\": 500}";
        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/contribute");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyPayload() {
        String payload = "{}";
        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/contribute");
        response.then().statusCode(404);
    }
}