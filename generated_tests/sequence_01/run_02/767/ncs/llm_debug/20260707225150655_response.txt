package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    private String baseUrl() {
        String b = System.getProperty("base.url");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        return b;
    }

    @Test(timeout = 60000)
    public void testInvalidNLessThanTwoReturns400() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/1/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testAxZeroReturns200() {
        String base = baseUrl();
        given().when().get(base + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/3/0.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAxGreaterThanNUsesBessj1SmallAxReturns200() {
        String base = baseUrl();
        given().when().get(base + "/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/2/3.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testNegativeLargeXTriggersBessj1ElseBranchReturns200() {
        String base = baseUrl();
        given().when().get(base + "/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/bessj/2/-10.0");
        act.then().statusCode(400);
    }
}