package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {
    private static final String BASE = Optional.ofNullable(System.getProperty("api.base.url"))
            .orElse(Optional.ofNullable(System.getenv("API_BASE_URL")).orElse("http://localhost:8080"));

    @Test(timeout = 60000)
    public void testDefaultResult_returns0() {
        given().when().get(BASE + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/api/notypevar/{i}/{s}", 0, "a");
        Assert.assertEquals("0", res.asString());
    }

    @Test(timeout = 60000)
    public void testCompareToBranch_returns2() {
        given().when().get(BASE + "/api/pat/{txt}", "example").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/api/notypevar/{i}/{s}", 5, "z");
        Assert.assertEquals("2", res.asString());
    }

    @Test(timeout = 60000)
    public void testSumBranch_overriddenByGreater_returns3() {
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "add", "1", "2").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/api/notypevar/{i}/{s}", 28, "a");
        Assert.assertEquals("3", res.asString());
    }

    @Test(timeout = 60000)
    public void testConcatEqualsBranch_executed_status200() {
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "add", "0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/notypevar/{i}/{s}", 7, "irrelevant").then().statusCode(200);
    }
}