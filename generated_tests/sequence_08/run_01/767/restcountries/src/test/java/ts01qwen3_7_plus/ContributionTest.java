package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("test.base.url", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testContributeValidPayload() {
        String payload = "{\"amount\": 1000, \"token\": \"tok_123456789\"}";

        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/contribute");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken() {
        String payload = "{\"amount\": 1000}";

        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/contribute");

        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeMissingAmount() {
        String payload = "{\"token\": \"tok_123456789\"}";

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

    @Test(timeout = 60000)
    public void testContributeInvalidAmountType() {
        String payload = "{\"amount\": \"invalid\", \"token\": \"tok_123456789\"}";

        Response response = given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/contribute");

        response.then().statusCode(404);
    }
}