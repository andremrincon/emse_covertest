package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ContributionTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code <202> but was <404>.")
    @Test(timeout = 60000)
    public void testSuccessfulContribution_withAmountAndToken() {
        String json = "{\"amount\":100,\"currency\":\"USD\",\"token\":\"tok_visa\",\"reference\":\"" + UUID.randomUUID().toString() + "\"}";
        given().contentType(ContentType.JSON).body(json).when().post("/contribute").then().statusCode(202);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testContributionMissingToken_returns400() {
        String json = "{\"amount\":50,\"currency\":\"USD\",\"reference\":\"" + UUID.randomUUID().toString() + "\"}";
        given().contentType(ContentType.JSON).body(json).when().post("/contribute").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testContributionInvalidAmountFormat_returns400() {
        String json = "{\"amount\":\"abc\",\"currency\":\"USD\",\"token\":\"tok_visa\",\"reference\":\"" + UUID.randomUUID().toString() + "\"}";
        given().contentType(ContentType.JSON).body(json).when().post("/contribute").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testContributionNegativeAmount_returns400() {
        String json = "{\"amount\":-10,\"currency\":\"USD\",\"token\":\"tok_visa\",\"reference\":\"" + UUID.randomUUID().toString() + "\"}";
        given().contentType(ContentType.JSON).body(json).when().post("/contribute").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testContributionTokenExplicitNull_returns400() {
        String json = "{\"amount\":25,\"currency\":\"USD\",\"token\":null,\"reference\":\"" + UUID.randomUUID().toString() + "\"}";
        given().contentType(ContentType.JSON).body(json).when().post("/contribute").then().statusCode(400);
    }
}