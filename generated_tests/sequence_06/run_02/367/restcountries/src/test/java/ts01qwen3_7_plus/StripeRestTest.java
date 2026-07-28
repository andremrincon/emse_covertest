package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testContributeNull() {
        Response response = given()
                .contentType("application/json;charset=utf-8")
            .when()
                .post("/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeBlankToken() {
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"token\": \"\", \"amount\": 100}")
            .when()
                .post("/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeValidToken() {
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"token\": \"tok_123456789\", \"amount\": 500}")
            .when()
                .post("/contribute");

        response.then().statusCode(400);
    }
}