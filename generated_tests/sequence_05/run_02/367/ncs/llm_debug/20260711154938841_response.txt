package ts01gpt_5_mini;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Test;
import org.junit.BeforeClass;
import io.restassured.response.Response;

public class ExpintTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        base = System.getProperty("baseUrl",
                System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testNegativeN_Returns400() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/-1/1.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testNegativeX_Returns400() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/3/-1.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testNEqualsZero_Returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/0/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testXEqualsZeroForN3_Returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/3/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeriesBranch_N3_X0Point1_Returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/3/0.1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testContinuedFraction_N3_X2Point5_Returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/expint/3/2.5");
        act.then().statusCode(200);
    }
}