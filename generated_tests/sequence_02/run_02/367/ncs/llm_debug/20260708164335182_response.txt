package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    private static final String BASE;
    static {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE");
        if (prop != null && !prop.isEmpty()) {
            BASE = prop;
        } else if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testFisher_oddOdd_loops_executes_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 5, 5, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_oddEven_branch_executes_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 3, 6, 0.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_evenEven_zeroX_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 10, 4, 0.0);
        resp.then().statusCode(200);
    }
}