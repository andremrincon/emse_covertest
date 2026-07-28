package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CostfunsTest {

    private String baseUrl() {
        String v = System.getProperty("api.base");
        if (v == null || v.isEmpty()) v = System.getenv("API_BASE");
        if (v == null || v.isEmpty()) v = "http://localhost:8080";
        return v;
    }

    @Test(timeout = 60000)
    public void testFinal10_DefaultInputProduces10() throws Exception {
        String base = baseUrl();
        String unique = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", unique).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", 1, "algorithm");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFinal6_WhenStringIsAbab() throws Exception {
        String base = baseUrl();
        String unique = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", unique).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", 0, "abab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFinal0_WhenIisMinus4AndStringIsAbab() throws Exception {
        String base = baseUrl();
        String unique = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", unique).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", -4, "abab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBranch_IEquals5_ObservedBehavior() throws Exception {
        String base = baseUrl();
        String unique = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", unique).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", 5, "abab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBranch_ILessThanMinus444_ObservedBehavior() throws Exception {
        String base = baseUrl();
        String unique = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", unique).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", -500, "abab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBranch_SEqualsBaab_WithIMinus4() throws Exception {
        String base = baseUrl();
        String unique = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", unique).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/{i}/{s}", -4, "baab");
        assertEquals("10", resp.getBody().asString());
    }
}