package ts01qwen3_7_plus;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testContributeValidPayload() {
        String jsonPayload = "{\"amount\": 1000, \"token\": \"tok_valid_12345\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(baseUrl + "/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken() {
        String jsonPayload = "{\"amount\": 1000}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(baseUrl + "/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeMissingAmount() {
        String jsonPayload = "{\"token\": \"tok_valid_12345\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(baseUrl + "/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyPayload() {
        String jsonPayload = "{}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(baseUrl + "/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeInvalidJsonFormat() {
        String jsonPayload = "invalid_json_format";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(baseUrl + "/contribute");

        response.then().statusCode(400);
    }
}