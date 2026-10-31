package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CostfunsTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("api.base");
        RestAssured.baseURI = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_sBaab_returns200() {
        String uniq = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uniq).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 5, "baab");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444_sAbabab_returns200() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "ababab");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666_sAbabab_returns200() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 700, "ababab");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsMinus4_sAbabab_bodyZero() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "ababab");
        assertEquals(200, resp.getStatusCode());
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_compareToEqualCase_sAbabba_returns200() {
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zebra", "yak", "x-ray", "wolf").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 123, "ababba");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_genericCase_iZero_sAlgorithm_returns200() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "algorithm");
        assertEquals(200, resp.getStatusCode());
    }
}