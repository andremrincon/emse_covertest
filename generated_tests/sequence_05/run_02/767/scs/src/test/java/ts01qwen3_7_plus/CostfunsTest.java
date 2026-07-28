package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchIEquals5AndSEqualsBaab() {
        given().pathParam("i", 0).pathParam("s", "test").when().get("/api/costfuns/{i}/{s}").then().statusCode(lessThan(300));
        given().pathParam("i", 5).pathParam("s", "baab").when().get("/api/costfuns/{i}/{s}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchILessThanNeg444() {
        given().pathParam("i", 0).pathParam("s", "test").when().get("/api/costfuns/{i}/{s}").then().statusCode(lessThan(300));
        given().pathParam("i", -500).pathParam("s", "xyz").when().get("/api/costfuns/{i}/{s}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchIGreaterThan666() {
        given().pathParam("i", 0).pathParam("s", "test").when().get("/api/costfuns/{i}/{s}").then().statusCode(lessThan(300));
        given().pathParam("i", 700).pathParam("s", "xyz").when().get("/api/costfuns/{i}/{s}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchIGreaterThan666False() {
        given().pathParam("i", 0).pathParam("s", "test").when().get("/api/costfuns/{i}/{s}").then().statusCode(lessThan(300));
        given().pathParam("i", 600).pathParam("s", "xyz").when().get("/api/costfuns/{i}/{s}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchINotEqualsNeg4False() {
        given().pathParam("i", 0).pathParam("s", "test").when().get("/api/costfuns/{i}/{s}").then().statusCode(lessThan(300));
        given().pathParam("i", -4).pathParam("s", "ababba").when().get("/api/costfuns/{i}/{s}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchSCompareToLessThanZero() {
        given().pathParam("i", 0).pathParam("s", "test").when().get("/api/costfuns/{i}/{s}").then().statusCode(lessThan(300));
        given().pathParam("i", 0).pathParam("s", "aab").when().get("/api/costfuns/{i}/{s}").then().statusCode(200);
    }
}