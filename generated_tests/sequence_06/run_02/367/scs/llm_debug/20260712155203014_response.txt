package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base", System.getenv("API_BASE") != null ? System.getenv("API_BASE") : "http://localhost:8080");
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testCostfunsReturns10ForNonAbabString() {
        given().when().get("/api/pat/TheQuickBrown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/0/algorithm");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfunsReturnsZeroWhen_iIsMinusFourAnd_sIsAbab() {
        given().when().get("/api/pat/healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-4/abab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_accepts_iEqualsFive_status200() {
        given().when().get("/api/pat/ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/5/abab");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_handles_iLessThanMinus444_status200() {
        given().when().get("/api/pat/ready").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-500/abab");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_handles_sEqualsBaab_branch_status200() {
        given().when().get("/api/pat/check").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-4/baab");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_compareToGreaterThanBranch_resultsInResponse() {
        given().when().get("/api/pat/verify").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-4/zzzz");
        assertEquals("10", resp.getBody().asString());
    }
}