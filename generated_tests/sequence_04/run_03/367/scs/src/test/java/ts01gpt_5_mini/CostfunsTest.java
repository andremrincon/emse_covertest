package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    private static String getBase() {
        String b = System.getProperty("api.baseUrl");
        if (b != null && !b.isEmpty()) return b;
        b = System.getenv("API_BASE_URL");
        if (b != null && !b.isEmpty()) return b;
        return "http://localhost:8080";
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = getBase();
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_returnsExpectedBody() {
        given().when().get("/api/pat/{txt}", "arrange1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 5, "abab");
        Assert.assertEquals("10", resp.asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_negativeI_triggersMultipleIntegerBranches() {
        given().when().get("/api/pat/{txt}", "arrange2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "abab");
        Assert.assertEquals("10", resp.asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_largeI_and_greaterString_triggersCompareBranches() {
        given().when().get("/api/pat/{txt}", "arrange3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 700, "zzzz");
        Assert.assertEquals("10", resp.asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEqualsBaab_executesEqualsBranch() {
        given().when().get("/api/pat/{txt}", "arrange4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "baab");
        Assert.assertEquals("10", resp.asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_stringEqualToConcatenation_triggersCompareToEquals() {
        given().when().get("/api/pat/{txt}", "arrange5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "ababba");
        Assert.assertEquals("10", resp.asString());
    }
}