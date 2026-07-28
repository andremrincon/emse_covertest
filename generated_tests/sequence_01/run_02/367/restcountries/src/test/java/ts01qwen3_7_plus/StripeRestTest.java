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
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testContributeNullContribution() {
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .when()
                .post("/contribute");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeNullToken() {
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\": 100}")
                .when()
                .post("/contribute");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyToken() {
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\": 100, \"token\": \"\"}")
                .when()
                .post("/contribute");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeBlankToken() {
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\": 100, \"token\": \"   \"}")
                .when()
                .post("/contribute");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeValidToken() {
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\": 100, \"token\": \"tok_valid\"}")
                .when()
                .post("/contribute");
        response.then().statusCode(404);
    }
}