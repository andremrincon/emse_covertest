package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammqUsesGserBranchReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "5.5", "2.3");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqUsesGcfBranchReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "0.001", "1000.0");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqGserHandlesXZeroReturnsResultOneInBody() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "5.5", "0.0");
        String body = resp.asString();
        assertTrue(body.contains("1.0"));
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAProduces400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "-1.0", "2.3");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqNonNumericAProduces400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "abc", "2.3");
        assertEquals(400, resp.getStatusCode());
    }
}