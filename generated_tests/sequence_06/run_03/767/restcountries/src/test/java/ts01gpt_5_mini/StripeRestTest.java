package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void init() {
        String url = System.getProperty("BASE_URL");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken_returnsBadRequest() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\":100,\"token\":\"\"}")
                .when()
                .post("/contribute");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithNoBody_returnsBadRequest() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = given()
                .contentType("application/json;charset=utf-8")
                .when()
                .post("/contribute");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithToken_triggersStripeError_returnsBadRequest() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response act = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\":500,\"token\":\"tok_visa_12345\"}")
                .when()
                .post("/contribute");
        act.then().statusCode(400);
    }
}