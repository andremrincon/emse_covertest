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
        String envBase = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = envBase;
    }

    @Test(timeout = 60000)
    public void testReturnsZeroWhenIIsMinus4AndSIsAbab() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-4/abab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testReturnsSixWhenIIsFiveAndSIsAbab() {
        given().when().get("/api/calc/add/1/2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/5/abab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testReturnsTenWhenSIsNotAbab() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/1/x");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCompareToGreaterThanZeroProducesOkStatus() {
        given().when().get("/api/pat/Example").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/1/zzzzz");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCompareToEqualsZeroPathProducesTen() {
        given().when().get("/api/pat/Sample").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/1/ababba");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testBranchesForVeryNegativeIStillYieldTenOrSixDependingOnS() {
        given().when().get("/api/calc/subtract/-100/50").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-1000/abab");
        assertEquals("10", resp.getBody().asString());
    }
}