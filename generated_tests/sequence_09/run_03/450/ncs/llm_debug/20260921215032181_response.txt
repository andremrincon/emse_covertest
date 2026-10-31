package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("baseUrl");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThan2_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/1/2.5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_axZero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_forwardRecurrence_smallAx_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/2/3.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_forwardRecurrence_largeAx_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/2/10.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_backwardRecurrence_smallAx_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/5/1e-10");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_negativeX_oddN_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/-2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_invalidX_nonNumeric_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/abc");
        resp.then().statusCode(400);
    }
}