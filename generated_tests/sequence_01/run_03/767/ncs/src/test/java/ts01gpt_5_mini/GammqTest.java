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
        String env = System.getProperty("BASE_URL", System.getenv("BASE_URL"));
        if (env == null || env.isEmpty()) {
            RestAssured.baseURI = "http://localhost:8080";
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void testGammq_GserBranch_Returns200_forTypicalValues() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "5.5", "2.3");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_GcfBranch_Returns200_forLargeX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "5.5", "1000.0");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_GserZeroX_Returns200_forXEqualsZero() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "5.5", "0.0");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_GserNegativeX_Returns400_forNegativeX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "5.5", "-1.0");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammq_GcfEdgeConditions_Returns200_forVeryLargeAAndSmallX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "1000000.0", "1e-10");
        assertEquals(200, resp.getStatusCode());
    }
}