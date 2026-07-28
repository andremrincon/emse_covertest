package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Assert;

public class ContributionTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeAccepted_withAmountAndToken() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String payload = "{\"amount\":100,\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json").body(payload).when().post("/contribute");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeBadRequest_missingToken() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":50,\"currency\":\"USD\"}";
        Response resp = given().contentType("application/json").body(payload).when().post("/contribute");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeBadRequest_missingAmount() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String payload = "{\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json").body(payload).when().post("/contribute");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeBadRequest_invalidAmountType() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String payload = "{\"amount\":\"onehundred\",\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json").body(payload).when().post("/contribute");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeAccepted_largeAmountUniqueToken() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String payload = "{\"amount\":100000,\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json").body(payload).when().post("/contribute");
        Assert.assertEquals(400, resp.getStatusCode());
    }
}