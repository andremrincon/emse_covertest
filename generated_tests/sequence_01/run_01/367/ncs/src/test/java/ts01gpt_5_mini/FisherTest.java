package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String fromProp = System.getProperty("api.base");
        String fromEnv = System.getenv("API_BASE_URL");
        BASE = fromProp != null ? fromProp : (fromEnv != null ? fromEnv : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisher_BothOdd_Returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 3, 3, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_AOdd_BEven_Returns200() {
        given().when().get(BASE + "/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 3, 4, 0.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_AEven_BOdd_Returns200() {
        given().when().get(BASE + "/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 4, 3, 0.2);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_BothEven_Returns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 4, 4, 0.1);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_LargeMOdd_NSmall_Returns200() {
        given().when().get(BASE + "/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 101, 3, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_ExampleParameters_Returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 10, 5, 0.0);
        resp.then().statusCode(200);
    }
}