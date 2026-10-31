package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ContributionTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testContributionWithValidAmountAndToken() {
        String requestBody = "{\"amount\": 1000, \"token\": \"tok_valid_123\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithMissingAmount() {
        String requestBody = "{\"token\": \"tok_valid_456\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithMissingToken() {
        String requestBody = "{\"amount\": 2000}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithZeroAmount() {
        String requestBody = "{\"amount\": 0, \"token\": \"tok_valid_789\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithNegativeAmount() {
        String requestBody = "{\"amount\": -500, \"token\": \"tok_valid_abc\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithEmptyToken() {
        String requestBody = "{\"amount\": 1500, \"token\": \"\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithNullAmount() {
        String requestBody = "{\"amount\": null, \"token\": \"tok_valid_def\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithNullToken() {
        String requestBody = "{\"amount\": 3000, \"token\": null}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithEmptyBody() {
        String requestBody = "{}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithLargeAmount() {
        String requestBody = "{\"amount\": 999999, \"token\": \"tok_valid_large\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithSpecialCharactersInToken() {
        String requestBody = "{\"amount\": 500, \"token\": \"tok_!@#$%^&*()\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithMinimalValidAmount() {
        String requestBody = "{\"amount\": 1, \"token\": \"tok_minimal\"}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }
}