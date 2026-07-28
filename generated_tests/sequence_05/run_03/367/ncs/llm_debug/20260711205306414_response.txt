package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_InvalidParameters_ShouldReturn400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, -1);
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_NZero_ShouldReturn200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 0, 1);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NGreaterThan1_ShouldReturn200() {
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 0);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_ContinuedFraction_Path_ShouldReturn200() {
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_Series_With_Psi_Computation_ShouldReturn200() {
        given().when().get("/api/bessj/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 2, 0.1);
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpint_Series_NEquals1_ShouldReturn200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 1, 0.1);
        Assert.assertEquals(200, resp.getStatusCode());
    }
}