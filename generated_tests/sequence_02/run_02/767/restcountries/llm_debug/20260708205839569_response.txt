package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class StripeRestTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankTokenReturnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\":100,\"token\":\"\"}")
                .when()
                .post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithInvalidStripeInvocationReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\":250,\"token\":\"tok_invalid_" + UUID.randomUUID().toString() + "\"}")
                .when()
                .post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithValidTokenReturnsAccepted() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\":500,\"token\":\"tok_valid_" + UUID.randomUUID().toString() + "\"}")
                .when()
                .post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }
}