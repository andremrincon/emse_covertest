package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        String base = System.getProperty("API_BASE_URL", env == null ? "http://localhost:8080" : env);
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatShortLength_returns0() {
        given().when().get("/api/pat/{txt}", "warmup-short").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", "sometext", "ab");
        String body = r.asString();
        Assert.assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testPatFound_returns1() {
        given().when().get("/api/pat/{txt}", "warmup-found").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", "xxabcyy", "abc");
        String body = r.asString();
        Assert.assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void testReverseFound_returns2() {
        given().when().get("/api/pat/{txt}", "warmup-rev").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", "xxcbayy", "abc");
        String body = r.asString();
        Assert.assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void testBothNonAdjacent_returnsIndex() {
        given().when().get("/api/pat/{txt}", "warmup-both").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", "ABAxxxABA", "ABA");
        String body = r.asString();
        Assert.assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void testBothAdjacentPalindrome_returnsIndex() {
        given().when().get("/api/pat/{txt}", "warmup-adj").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", "ABAABA", "ABA");
        String body = r.asString();
        Assert.assertEquals("0", body);
    }
}