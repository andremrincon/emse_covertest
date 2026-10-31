package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThan2_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/1/2.5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_xZero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_smallAx_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/4.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_largeAx_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/10.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_largeAxNegative_bessj1HandlesNegative_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/-10.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axLessOrEqualN_negativeX_oddN_signFlips_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/5/-2.0");
        resp.then().statusCode(200);
    }
}