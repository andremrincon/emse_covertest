package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = System.getProperty("api.base", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammqGserPath_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfPath_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 1.0, 1000.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqNegativeX_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 5.0, -1.0);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqNonPositiveA_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/{a}/{x}", 0.0, 1.0);
        act.then().statusCode(400);
    }
}