package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private static String base;

    @BeforeClass
    public static void init() {
        String cfg = System.getProperty("api.baseUrl", System.getenv("API_BASE_URL"));
        base = (cfg == null || cfg.isEmpty()) ? "http://localhost:8080" : cfg;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderReturnsIncreasingBody() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/ordered4/aaaaa/bbbbb/ddddd/ccccc");
        act.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderReturnsDecreasingBody() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx");
        act.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testShortWReturnsUnorderedBody() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/ordered4/a/bbbbb/ccccc/ddddd");
        act.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsButNoOrderReturnsStatus200() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/ordered4/aaaaa/zzzzz/mmmmm/qqqqq");
        act.then().statusCode(200);
    }
}