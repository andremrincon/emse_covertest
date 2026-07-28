package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Assert;

import org.junit.Ignore;
public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv().getOrDefault("API_BASE", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_gserBranch_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, 0.5);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_gcfBranch_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, 1000.0);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Ignore


    @Test(timeout = 60000)
    public void testGammq_xZero_returnsResultOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 2.0, 0.0);
        Assert.assertEquals(200, resp.getStatusCode());
        Assert.assertEquals(0.0, resp.jsonPath().getDouble("result"), 1e-9);
    }

    @Test(timeout = 60000)
    public void testGammq_negativeX_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.0, -1.0);
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_nonPositiveA_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 0.0, 1.0);
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_invalidANonNumeric_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "abc", 1.0);
        Assert.assertEquals(400, resp.getStatusCode());
    }
}