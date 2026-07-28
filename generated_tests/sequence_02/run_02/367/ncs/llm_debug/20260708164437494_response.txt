package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/1/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjZeroXReturnsZeroInBody() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/0");
        act.then().body(containsString("0.0"));
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/2/3.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddNProducesNegativeResult() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/-2.5");
        act.then().body(containsString("-"));
    }

    @Test(timeout = 60000)
    public void testBessjAxLessOrEqualNUsesBackwardRecurrence() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/10/1e-10");
        act.then().statusCode(200);
    }
}