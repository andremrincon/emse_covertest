package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Before
    public void setUp() {
        String envUrl = System.getenv("BASE_URL");
        String baseUrl = System.getProperty("base.url", envUrl != null ? envUrl : "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\": 100}")
                .when()
                .post("/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithToken() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\": 100, \"token\": \"tok_test\"}")
                .when()
                .post("/contribute");

        response.then().statusCode(400);
    }
}