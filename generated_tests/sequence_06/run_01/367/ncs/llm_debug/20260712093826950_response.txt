package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_WithZeroX_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, 0.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_WithSmallX_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, 0.001);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gcf_WithLargeX_returns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, 1000.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gcf_BoundaryXEqualsAPlusOne_returns200() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 2.0, 3.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidANegative_returns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", -1.0, 2.3);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidANonNumeric_returns400() {
        given().when().get("/api/remainder/17/4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/abc/{x}", 2.3);
        resp.then().statusCode(400);
    }
}