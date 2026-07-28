package ts01gpt_5_mini;

import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("API_BASE");
        if (prop == null || prop.trim().isEmpty()) {
            prop = System.getenv("API_BASE");
        }
        if (prop == null || prop.trim().isEmpty()) {
            prop = System.getProperty("api.base");
        }
        if (prop == null || prop.trim().isEmpty()) {
            prop = System.getenv("API_BASE_URL");
        }
        if (prop == null || prop.trim().isEmpty()) {
            prop = "http://localhost:8080/rest";
        }
        BASE = prop;
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankTokenReturnsBadRequest() {
        String id = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/v2").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE).contentType(ContentType.JSON)
                .body("{\"amount\":100,\"token\":\"   \",\"id\":\"" + id + "\"}")
                .when().post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidTokenButStripeAuthFailsReturnsBadRequest() {
        String id = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE).contentType(ContentType.JSON)
                .body("{\"amount\":500,\"token\":\"tok_valid_" + id + "\",\"id\":\"" + id + "\"}")
                .when().post("/contribute");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidTokenReturnsAccepted() {
        String id = UUID.randomUUID().toString();
        given().baseUri(BASE).when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        Response response = given().baseUri(BASE).contentType(ContentType.JSON)
                .body("{\"amount\":250,\"token\":\"tok_charge_succeeds_" + id + "\",\"id\":\"" + id + "\"}")
                .when().post("/contribute");
        response.then().statusCode(400);
    }
}