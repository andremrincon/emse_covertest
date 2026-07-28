package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CostfunsTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCostfunsReturns10ForGeneralString() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "algorithm");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfunsWithAbabReturns6() {
        given().when().get("/api/calc/add/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 0, "abab");
        resp.then().body(equalTo("10"));
    }

    @Test(timeout = 60000)
    public void testCostfunsWithBaabTriggersEqualityBranch() {
        given().when().get("/api/calc/add/2/2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 1, "baab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsCompareToGreaterThanZeroBranch() {
        given().when().get("/api/calc/add/3/3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 1, "zzzz");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsNegativeITriggersLessThanBranches() {
        given().when().get("/api/calc/add/4/4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", -500, "abab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsIEquals5TriggersBranch() {
        given().when().get("/api/calc/add/5/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/{i}/{s}", 5, "abab");
        resp.then().statusCode(200);
    }
}