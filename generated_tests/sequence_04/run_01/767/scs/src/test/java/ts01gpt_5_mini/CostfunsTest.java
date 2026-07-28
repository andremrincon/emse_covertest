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

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCostfuns_finalTen_for_baab_and_i_minus4() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-4/baab");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsFive_returns_200() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        String s = UUID.randomUUID().toString();
        Response resp = given().when().get("/api/costfuns/5/" + s);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanNegative444_returns_200() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        String s = UUID.randomUUID().toString();
        Response resp = given().when().get("/api/costfuns/-500/" + s);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666_returns_200() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        String s = UUID.randomUUID().toString();
        Response resp = given().when().get("/api/costfuns/700/" + s);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCostfuns_compareTo_equal_edge_ababba_results_in_10() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-4/ababba");
        assertEquals("10", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEquals_abab_returns_0() {
        given().when().get("/api/calc/add/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/costfuns/-4/abab");
        assertEquals("10", resp.getBody().asString());
    }
}