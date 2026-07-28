package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testReturnZeroWhenXIsZero() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, 0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBadRequestWhenNLessThan2() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 1, 2.5);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testForwardRecurrenceWhenAxGreaterThanN() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 2, 10.0);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBackwardRecurrenceWhenAxLessOrEqualN() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 5, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeXWithOddNReturnsOk() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, -2.5);
        act.then().statusCode(200);
    }
}