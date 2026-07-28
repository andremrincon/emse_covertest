package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeMissingTokenReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\":100}")
                .when()
                .post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithTokenTriggersStripeExceptionReturns400() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        Response resp = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\":150,\"token\":\"" + token + "\"}")
                .when()
                .post("/contribute");
        resp.then().statusCode(400);
    }
}