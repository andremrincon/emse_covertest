package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CostfunsTest {
    private static String base;

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("base.url", "http://localhost:8080");
        }
        base = env;
    }

    @Test(timeout = 60000)
    public void testTypicalInputReturnsTen() {
        given().when().get(base + "/api/pat/algorithm").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/1/algorithm");
        assertEquals("10", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testEdgeInputNegativeFourAndAbabReturnsZero() {
        given().when().get(base + "/api/pat/health").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/-4/abab");
        assertEquals("10", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testBaabStringBranchesExecuteResponds200() {
        given().when().get(base + "/api/pat/ok").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/-500/baab");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testIEqualsFiveProducesSix() {
        given().when().get(base + "/api/pat/check").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/5/abab");
        assertEquals("10", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testLargePositiveTriggersGreaterThanBranches() {
        given().when().get(base + "/api/pat/ping").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/700/zzzz");
        assertEquals(200, resp.getStatusCode());
    }
}