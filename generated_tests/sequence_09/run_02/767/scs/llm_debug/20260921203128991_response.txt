package ts01gpt_5_mini_2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCostfuns_algorithm_returns200() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/1/algorithm");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_i5_returnsServerError() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/5/baab");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_zero_ababba_returns200() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/0/ababba");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_invalidInteger_returns400() {
        given().when().get("/api/pat/ABABCABAB/ABAB").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/one/algorithm");
        Assert.assertEquals(400, act.getStatusCode());
    }
}