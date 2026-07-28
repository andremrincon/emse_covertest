package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CostfunsTest {
    private final String base = System.getProperty("api.base",
            System.getenv().containsKey("API_BASE") ? System.getenv("API_BASE") : "http://localhost:8080");

    @Test(timeout = 60000)
    public void test_allConditionsFalse_returns0() {
        given().when().get(base + "/api/title/male/Smith").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/-4/abab").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_iNotMinus4_and_sAbab_returns6() {
        given().when().get(base + "/api/pat/hello").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/0/abab").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_sNotAbab_returns10() {
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/1/a").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_sEqualsBaab_and_highI_exercisesStringEqualsAndCompareTo() {
        given().when().get(base + "/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/1000/baab").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_iEquals5_and_sAbab_returns6() {
        given().when().get(base + "/api/cookie/session-id/abc-123-xyz-789/example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/5/abab").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_iLessThanMinus444_and_sAbab_returns6() {
        given().when().get(base + "/api/ordered4/zebra/yak/x-ray/wolf").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/costfuns/-500/abab").then().statusCode(200).extract().response();
        assertEquals("10", resp.getBody().asString());
    }
}