package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private static final String BASE = initBase();

    private static String initBase() {
        String p = System.getProperty("api.base");
        if (p != null && !p.isEmpty()) return p;
        String e = System.getenv("API_BASE");
        if (e != null && !e.isEmpty()) return e;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGammqUsesGserConverges() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/gammq/{a}/{x}", "5.5", "0.001");
        Assert.assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqUsesGcfConverges() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/gammq/{a}/{x}", "5.5", "1000.0");
        Assert.assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqXZeroGserEarlyReturn() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/gammq/{a}/{x}", "5.5", "0.0");
        Assert.assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqNegativeXReturns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/gammq/{a}/{x}", "5.5", "-1.0");
        Assert.assertEquals(400, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqAZeroReturns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/gammq/{a}/{x}", "0.0", "2.0");
        Assert.assertEquals(400, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAStringReturns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/gammq/{a}/{x}", "abc", "2.0");
        Assert.assertEquals(400, r.getStatusCode());
    }
}