package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class BessjTest {

    private String base() {
        String b = System.getProperty("baseUrl");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        return b;
    }

    @Test(timeout = 60000)
    public void testBessj_invalidN_negative_returns400() {
        String base = base();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/-5/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_xZero_returns200() {
        String base = base();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/2/0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_forwardRecurrence_axGreaterThanN_returns200() {
        String base = base();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/3/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_downwardRecurrence_smallX_returns200() {
        String base = base();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/50/1e-10");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_nonNumericN_returns400() {
        String base = base();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/abc/2.5");
        act.then().statusCode(400);
    }
}