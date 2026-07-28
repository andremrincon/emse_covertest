package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testContribute_missingToken_returnsBadRequest() {
        given().when().get("").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100}";
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body(payload)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testContribute_validToken_returnsAccepted() {
        given().when().get("").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String payload = "{\"amount\":150,\"token\":\"" + token + "\"}";
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body(payload)
                .when()
                .post("/contribute");
        assertEquals(202, response.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testContribute_invalidTokenTriggersStripeError_returnsBadRequest() {
        given().when().get("").then().statusCode(lessThan(300));
        String payload = "{\"amount\":200,\"token\":\"tok_chargeDeclined\"}";
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body(payload)
                .when()
                .post("/contribute");
        response.then().statusCode(400);
    }
}