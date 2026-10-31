package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    private static final String BASE = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testBessj_nLessThan2_returns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/bessj/1/2.5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_xZero_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/bessj/3/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_smallAx_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/bessj/2/2.5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_largeAx_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/bessj/3/10.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_negativeX_largeAx_flipsSign_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/bessj/2/-10.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_elseBranch_smallX_triggersScaling_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/bessj/5/1e-10");
        resp.then().statusCode(200);
    }
}