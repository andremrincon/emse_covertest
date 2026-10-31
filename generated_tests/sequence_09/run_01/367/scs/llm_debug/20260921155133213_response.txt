package ts01gpt_5_mini_3;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert;

public class NotyPevarTest {

    private final String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080");

    @Test(timeout = 60000)
    public void test_notypevar_returns_2_when_string_is_greater_than_hello() {
        given().when().get(baseUrl + "/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get(baseUrl + "/api/notypevar/2/world");
        Assert.assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_notypevar_returns_3_when_y_greater_than_5_and_other_branches_may_run() {
        given().when().get(baseUrl + "/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get(baseUrl + "/api/notypevar/7/zzz");
        Assert.assertEquals("3", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_notypevar_returns_0_when_no_conditions_met() {
        given().when().get(baseUrl + "/api/cookie/session-id/abc-123-xyz-789/example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get(baseUrl + "/api/notypevar/5/aaa");
        Assert.assertEquals("0", resp.asString());
    }
}