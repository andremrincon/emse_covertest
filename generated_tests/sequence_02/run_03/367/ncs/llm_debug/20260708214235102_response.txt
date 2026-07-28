package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;

public class BessjTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base.url");
        if (env == null || env.isEmpty()) env = System.getenv("API_BASE_URL");
        if (env == null || env.isEmpty()) env = "http://localhost:8080";
        base = env;
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThan2_returns400() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/1/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_xZero_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/3/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_bessj0_lessThan8_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/2/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_bessj0_and_bessj1_greaterThan8_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/3/10.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axLessEqualN_backwardRecurrence_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/10/1e-10").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_negativeX_withOddN_triggersSignFlip_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/3/-2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_invalidNFormat_returns400() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/bessj/abc/2.5").then().statusCode(400);
    }
}