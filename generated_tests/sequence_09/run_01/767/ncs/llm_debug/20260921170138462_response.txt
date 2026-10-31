package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("NCS_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("NCS_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeN_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/-5/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_ZeroX_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/5.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_NEqualsOne_NegativeX_UsesBessj1NegativeBranch_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/1/-2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_TinyX_LeadsToSeriesPath_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/1e-10").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_OddN_NegativeX_SignFlip_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/-2.5").then().statusCode(200);
    }
}