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
        String env = System.getenv("BASE_URL");
        RestAssured.baseURI = env != null && !env.isEmpty() ? env : System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testNLessThan2_returns400() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/1/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testXZero_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/0.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAxGreaterThanN_axLessThan8_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/5.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAxGreaterThanN_axGreaterThan8_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/10.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAxLessOrEqualN_backwardRecurrence_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/10/1.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeX_oddN_signHandled_returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/-2.5");
        act.then().statusCode(200);
    }
}