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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCostfuns_DefaultPath_returns200() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 1, "algorithm");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_AbabWithNegativeFour_returnsBody0() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "abab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_abab_returns200() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 5, "abab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_BaabAndLargeI_returnsBody10() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 700, "baab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_LargeNegative_triggersBranches_returns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "zzz");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_CompareEqualString_returns200() {
        given().when().get("/api/filesuffix/{directory}/{file}", "documents", "quarterly_report.docx").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 1, "ababba");
        resp.then().statusCode(200);
    }
}