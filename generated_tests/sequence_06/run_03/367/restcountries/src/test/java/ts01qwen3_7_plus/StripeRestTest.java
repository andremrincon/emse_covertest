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
        String baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{}")
                .when()
                .post("/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeBlankToken() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"token\": \"   \", \"amount\": 100}")
                .when()
                .post("/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeValidToken() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"token\": \"tok_valid_123\", \"amount\": 100}")
                .when()
                .post("/contribute");

        response.then().statusCode(400);
    }
}