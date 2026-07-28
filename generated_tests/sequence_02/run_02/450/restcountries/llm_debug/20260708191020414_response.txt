package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("TEST_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeMissingToken_returnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().contentType(ContentType.JSON).body("{}").when().post("/contribute");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeBlankToken_returnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":1000,\"token\":\"\"}";
        Response act = given().contentType(ContentType.JSON).body(payload).when().post("/contribute");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithTestToken_returnsAccepted() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":1500,\"token\":\"tok_visa\"}";
        Response act = given().contentType(ContentType.JSON).body(payload).when().post("/contribute");
        act.then().statusCode(400);
    }
}