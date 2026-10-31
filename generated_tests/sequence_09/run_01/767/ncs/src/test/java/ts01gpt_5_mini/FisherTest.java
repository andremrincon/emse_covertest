package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {
    private static String base;

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE");
        base = prop != null ? prop : (env != null ? env : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nOdd_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/fisher/{m}/{n}/{x}", 11, 5, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nEven_returns200() {
        given().when().get(base + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/fisher/{m}/{n}/{x}", 11, 4, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nOdd_returns200() {
        given().when().get(base + "/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/fisher/{m}/{n}/{x}", 10, 5, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nEven_returns200() {
        given().when().get(base + "/api/expint/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/fisher/{m}/{n}/{x}", 10, 4, 0.75);
        resp.then().statusCode(200);
    }
}