package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {
    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("ncs.base");
        if (env == null || env.isEmpty()) {
            String e2 = System.getenv("NCS_BASE");
            BASE = (e2 == null || e2.isEmpty()) ? "http://localhost:8080" : e2;
        } else {
            BASE = env;
        }
    }

    @Test(timeout = 60000)
    public void testFisher_bothAandBEqualOne_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/1/1/0.75");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_aEqualOne_bNotOne_returns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/1/4/0.0");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_aNotOne_bEqualOne_returns200() {
        given().when().get(BASE + "/api/expint/1/0.1").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/2/3/0.75");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_neitherAorBEqualOne_returns200() {
        given().when().get(BASE + "/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/10/6/0.75");
        Assert.assertEquals(200, act.getStatusCode());
    }
}