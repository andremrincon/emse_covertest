package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CostfunsTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCostfuns_allBranches_to10() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/700/baab");
        assertEquals("10", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_returnsOkStatus() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/5/algorithm");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444_returnsOkStatus() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/-500/algorithm");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_compareTo_greater_path_returnsOkStatus() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/costfuns/0/c");
        assertEquals(200, act.getStatusCode());
    }
}