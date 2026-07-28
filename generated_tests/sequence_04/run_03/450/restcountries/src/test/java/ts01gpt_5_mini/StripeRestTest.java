package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeWithNullBodyReturnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/json;charset=utf-8")
                .body("")
                .when()
                .post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyTokenReturnsBadRequestBodyContainsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"token\":\"\"}";
        Response resp = given()
                .contentType("application/json;charset=utf-8")
                .body(payload)
                .when()
                .post("/contribute");
        assertTrue(resp.getBody().asString().contains("Bad Request"));
    }

    @Test(timeout = 60000)
    public void testContributeWithWhitespaceTokenReturnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":150,\"token\":\"   \"}";
        Response resp = given()
                .contentType("application/json;charset=utf-8")
                .body(payload)
                .when()
                .post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithNonBlankTokenTriggersStripeExceptionReturnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok_" + UUID.randomUUID().toString();
        String payload = "{\"amount\":200,\"token\":\"" + token + "\"}";
        Response resp = given()
                .contentType("application/json;charset=utf-8")
                .body(payload)
                .when()
                .post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }
}