package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {
    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE");
        String prop = System.getProperty("api.base");
        if (env != null && !env.isEmpty()) BASE = env;
        else if (prop != null && !prop.isEmpty()) BASE = prop;
        else BASE = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionReturns200() {
        given().when().get(BASE + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/3/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesWithPsiReturns200() {
        given().when().get(BASE + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/3/0.1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNZeroReturns200() {
        given().when().get(BASE + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/0/1.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNOneSmallXReturns200() {
        given().when().get(BASE + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/1/0.1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNReturns400() {
        given().when().get(BASE + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/-1/1.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXWithNZeroReturns400() {
        given().when().get(BASE + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/0/0.0");
        act.then().statusCode(400);
    }
}