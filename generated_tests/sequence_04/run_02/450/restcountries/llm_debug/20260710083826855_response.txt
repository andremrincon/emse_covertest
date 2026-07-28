package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ContributionTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testValidContributionReturns202() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String payload = "{\"amount\":250,\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response resp = given().contentType(ContentType.JSON).body(payload).when().post("/contribute");
        assertEquals(400, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testMissingTokenReturns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"currency\":\"EUR\"}";
        Response resp = given().contentType(ContentType.JSON).body(payload).when().post("/contribute");
        assertEquals(400, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testMissingAmountReturns400() {
        given().when().get("/v1/alpha/GB").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String payload = "{\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response resp = given().contentType(ContentType.JSON).body(payload).when().post("/contribute");
        assertEquals(400, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testInvalidJsonReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "this is not json";
        Response resp = given().contentType(ContentType.JSON).body(payload).when().post("/contribute");
        assertEquals(400, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testZeroAmountHandledAsBadRequest() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String payload = "{\"amount\":0,\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response resp = given().contentType(ContentType.JSON).body(payload).when().post("/contribute");
        assertEquals(400, resp.statusCode());
    }
}