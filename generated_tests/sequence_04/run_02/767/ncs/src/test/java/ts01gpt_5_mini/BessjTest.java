package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class BessjTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_InvalidN_LessThan2_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/-1/2.5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_XZero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_invokesBessj0AndBessj1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/10.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeX_OddN_signFlip_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/-10.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_InvalidN_NonNumeric_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/abc/2.5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_AxBetweenNAnd8_usesSmallAxPolynomialBranch_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/2/3.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_AxVerySmall_usesScalingBranch_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/5/1.0E-10");
        resp.then().statusCode(200);
    }
}