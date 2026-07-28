package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("BASE_URL");
        if (env == null || env.isEmpty()) env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) env = "http://localhost:8080";
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testInvalidN_lessThan2_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/1/2.5");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/10.0");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_axLessOrEqualN_iterative_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/2.5");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_xZero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/0");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessj_xNegative_oddN_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/-2.5");
        assertEquals(200, act.getStatusCode());
    }
}