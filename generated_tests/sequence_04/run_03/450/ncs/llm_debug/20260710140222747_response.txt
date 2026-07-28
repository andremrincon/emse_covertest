package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class ExpintTest {

    private static final String BASE;
    static {
        String b = System.getProperty("BASE_URL");
        if (b == null) b = System.getenv("BASE_URL");
        if (b == null) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_Returns400() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/{n}/{x}", -1, 1.0);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NOne_Returns400() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/{n}/{x}", 1, 0.0);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NZero_XOne_Returns200() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/{n}/{x}", 0, 1.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XGreaterThanOne_ContinuedFraction_Returns200() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/{n}/{x}", 3, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesPath_Returns200() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/{n}/{x}", 3, 0.1);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NGreaterThanOne_Returns200() {
        given().when().get(BASE + "/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/expint/{n}/{x}", 3, 0.0);
        act.then().statusCode(200);
    }
}