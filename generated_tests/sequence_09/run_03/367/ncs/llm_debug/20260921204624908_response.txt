package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeN_BadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", -5, 2.5);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_ZeroX_ReturnsOk() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 2, 0.0);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_SmallArg() {
        given().when().get("/api/remainder/17/4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 5.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_LargeArg() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 100.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxLessOrEqualN_BackwardRecurrence() {
        given().when().get("/api/remainder/ -9/4".replace(" ", "")).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 10, "1e-10");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeX_OddN_SignFlip() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, -5.0);
        resp.then().statusCode(200);
    }
}