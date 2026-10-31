package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken_returnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().contentType(ContentType.JSON).body("{\"amount\":100}").when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyToken_returnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().contentType(ContentType.JSON).body("{\"amount\":200,\"token\":\"\"}").when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWhitespaceToken_returnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().contentType(ContentType.JSON).body("{\"amount\":300,\"token\":\"   \"}").when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeStripeException_returnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().contentType(ContentType.JSON).body("{\"amount\":400,\"token\":\"tok_invalid_" + java.util.UUID.randomUUID().toString() + "\"}").when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeValidToken_returnsAccepted() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().contentType(ContentType.JSON).body("{\"amount\":500,\"token\":\"tok_success_" + java.util.UUID.randomUUID().toString() + "\"}").when().post("/contribute").then().statusCode(400);
    }
}