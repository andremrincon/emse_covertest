package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_GserBranch_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, 2.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_GcfBranch_HighX_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, 1000.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidA_Negative_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", -1.0, 2.0);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_Boundary_XEqualsAPlusOne_UsesGcf_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 1.0, 2.0);
        act.then().statusCode(200);
    }
}