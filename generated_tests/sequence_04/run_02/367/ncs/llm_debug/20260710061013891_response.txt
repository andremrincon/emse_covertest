package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("api.base.url");
        if (cfg == null || cfg.isEmpty()) {
            String env = System.getenv("API_BASE_URL");
            RestAssured.baseURI = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        } else {
            RestAssured.baseURI = cfg;
        }
    }

    @Test(timeout = 60000)
    public void testBessj_InvalidN_String_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", "abc", "2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_NLessThanTwo_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 1, 2.5);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_XZero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, 0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanN_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, 5.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxLessOrEqualN_usesBackwardRecurrence_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 5, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_NegativeX_OddN_signFlip_behavior_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, -2.5);
        act.then().statusCode(200);
    }
}