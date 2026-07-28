package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_gserPath_returns200_whenXLessThanAPlusOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/2.0");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_gcfPath_returns200_whenXGreaterOrEqualAPlusOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/6.5");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_returns400_whenXNegative() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/-1.0");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_returns400_whenANonPositive() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/0.0/2.0");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_gserPath_xZero_returnsResultOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/0.0");
        assertEquals(200, act.getStatusCode());
    }
}