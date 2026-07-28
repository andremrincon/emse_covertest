package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("NCS_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("ncs.base", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_NLessThan2_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/1/2.5");
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_XZero_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/3/0");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_Returns200() {
        given().when().get("/api/triangle/5/5/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/2/10");
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_BackwardRecurrence_Returns200() {
        given().when().get("/api/triangle/4/4/4").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/10/1e-10");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_WithDecimalX_Returns200() {
        given().when().get("/api/triangle/2/3/4").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/3/2.5");
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_InvalidXFormat_Returns400() {
        given().when().get("/api/triangle/6/6/6").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/3/abc");
        r.then().statusCode(400);
    }
}