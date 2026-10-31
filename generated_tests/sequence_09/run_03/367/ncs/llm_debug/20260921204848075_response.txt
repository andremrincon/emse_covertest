package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("baseUrl");
        String base = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeN_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/-5/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_XZero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AsymptoticLargeX_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/10.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_IterativeNegativeX_returnsNegativeValue() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/bessj/3/-1.0E-10");
        Object o = r.jsonPath().get("resultAsDouble");
        if (o == null) o = r.jsonPath().get("value");
        Assert.assertTrue(o instanceof Number && ((Number) o).doubleValue() < 0.0);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_smallAx_polynomialPath_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/5.0").then().statusCode(200);
    }
}