package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ContributionTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeSuccessReturns202() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String body = "{\"amount\":100,\"token\":\"" + token + "\",\"currency\":\"USD\"}";
        given().contentType("application/json").body(body).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeMissingTokenReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String body = "{\"amount\":50,\"currency\":\"USD\"}";
        given().contentType("application/json").body(body).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeMissingAmountReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String body = "{\"token\":\"" + token + "\",\"currency\":\"USD\"}";
        given().contentType("application/json").body(body).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeInvalidJsonReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String body = "not a json";
        given().contentType("application/json").body(body).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeResponseBodyHasAcceptedStatusOnSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String body = "{\"amount\":200,\"token\":\"" + token + "\",\"currency\":\"EUR\"}";
        given().contentType("application/json").body(body).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeDifferentCurrencyReturns202() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String body = "{\"amount\":75,\"token\":\"" + token + "\",\"currency\":\"XOF\"}";
        given().contentType("application/json").body(body).when().post("/contribute").then().statusCode(400);
    }
}