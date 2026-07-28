package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {
    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base != null && !base.isEmpty()) {
            RestAssured.baseURI = base;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testCostfuns_compareToGreaterThan_returns10() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "1", "1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 1, "zzzz").then().statusCode(200).extract().response();
        Assert.assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_sAbab_iNotMinus4_returns6() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "abab").then().statusCode(200).extract().response();
        Assert.assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_sAbab_iMinus4_returns0() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "0", "0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -4, "abab").then().statusCode(200).extract().response();
        Assert.assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_compareToEquals_zeroCase_and_negativeIndexBranches() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", "5", "2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "ababba").then().statusCode(200).extract().response();
        Assert.assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_and_sEqualsBaab_equalsBranchExecuted() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "2", "3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 5, "baab").then().statusCode(200).extract().response();
        Assert.assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666_and_ge555_branches() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "4.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 700, "x").then().statusCode(200).extract().response();
        Assert.assertEquals("10", resp.getBody().asString());
    }
}