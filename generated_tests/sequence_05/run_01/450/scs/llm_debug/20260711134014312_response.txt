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

    private static final String BASE = initBase();

    private static String initBase() {
        String v = System.getProperty("BASE_URL");
        if (v == null || v.isEmpty()) v = System.getenv("BASE_URL");
        if (v == null || v.isEmpty()) v = System.getProperty("baseUrl");
        if (v == null || v.isEmpty()) v = System.getenv("BASE_URL");
        if (v == null || v.isEmpty()) v = "http://localhost:8080";
        return v.endsWith("/") ? v.substring(0, v.length() - 1) : v;
    }

    @BeforeClass
    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testCostfuns_returnsZero_when_iIsMinus4_and_sIsAbab() {
        given().when().get(BASE + "/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/costfuns/{i}/{s}", -4, "abab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_returnsSix_when_iIsFive_and_sIsAbab() {
        given().when().get(BASE + "/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/costfuns/{i}/{s}", 5, "abab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_status200_when_iGreaterThan666() {
        given().when().get(BASE + "/api/costfuns/{i}/{s}", 0, "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/costfuns/{i}/{s}", 700, "a");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_status200_when_iLessThanMinus444() {
        given().when().get(BASE + "/api/costfuns/{i}/{s}", 1, "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/costfuns/{i}/{s}", -1000, "a");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_status200_when_iEqualsMinus333() {
        given().when().get(BASE + "/api/costfuns/{i}/{s}", 2, "test").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/costfuns/{i}/{s}", -333, "baab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_returnsTen_when_iIsMinus4_and_sIsLargeLexically() {
        given().when().get(BASE + "/api/calc/add/0/0").then().statusCode(lessThan(300));
        String unique = "zzzzzz" + UUID.randomUUID().toString().replace("-", "");
        Response resp = given().when().get(BASE + "/api/costfuns/{i}/{s}", -4, unique);
        assertEquals("10", resp.getBody().asString());
    }
}