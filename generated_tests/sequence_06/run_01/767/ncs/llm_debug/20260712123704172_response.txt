package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
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
    public void testExpint_seriesPath_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", 3, 0.1);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_continuedFractionPath_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_nZero_specialCase_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", 0, 2.5);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_xZero_nGreaterThanOne_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", 3, 0);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_nZeroAndXZero_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", 0, 0);
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_negativeX_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/expint/{n}/{x}", 3, -1.0);
        res.then().statusCode(400);
    }
}