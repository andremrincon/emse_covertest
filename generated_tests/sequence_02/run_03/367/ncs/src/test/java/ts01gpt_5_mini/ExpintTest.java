package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertTrue;

public class ExpintTest {
    private static final String BASE;
    static {
        String prop = System.getProperty("base.url");
        String env = System.getenv("BASE_URL");
        if (prop != null && !prop.isEmpty()) BASE = prop;
        else if (env != null && !env.isEmpty()) BASE = env;
        else BASE = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpint_XGreaterThanOne_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/expint/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NZero_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/expint/0/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NGreaterThanOne_bodyContainsExpectedValue() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String body = given().when().get(BASE + "/api/expint/3/0").then().extract().asString();
        assertTrue(body.contains("0.5"));
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_returns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/expint/-1/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NOne_XZero_returns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/expint/1/0").then().statusCode(400);
    }
}