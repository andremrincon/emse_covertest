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
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammqUsesGserWhenXLessThanAPlusOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, 0.001);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqUsesGcfWhenXGreaterOrEqualAPlusOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, 1000.0);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqReturnsBadRequestForNegativeA() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", -1.0, 2.3);
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqGserHandlesXEqualZero() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, 0.0);
        assertEquals(200, act.getStatusCode());
    }
}