package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ContributionTest {

    @BeforeClass
    public static void init() {
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
    public void testValidContributionAccepted() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String token = "tok_" + UUID.randomUUID().toString();
        String json = "{\"amount\":1000,\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testMissingAmountBadRequest() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String token = "tok_" + UUID.randomUUID().toString();
        String json = "{\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testMissingTokenBadRequest() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String json = "{\"amount\":500,\"currency\":\"USD\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNegativeAmountBadRequest() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String token = "tok_" + UUID.randomUUID().toString();
        String json = "{\"amount\":-50,\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testInvalidAmountTypeBadRequest() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String token = "tok_" + UUID.randomUUID().toString();
        String json = "{\"amount\":\"notanumber\",\"currency\":\"USD\",\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testValidContributionWithExtraFieldsAccepted() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String token = "tok_" + UUID.randomUUID().toString();
        String json = "{\"amount\":2500,\"currency\":\"EUR\",\"token\":\"" + token + "\",\"description\":\"test contribution\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }
}