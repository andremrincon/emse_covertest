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
        RestAssured.baseURI = System.getProperty("api.base", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("api.port", "8080"));
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNReturns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/1/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjZeroXReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNUsesAsymptoticBranch() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/10");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessOrEqualThanNUsesDownwardRecurrence() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/10/1e-10");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXWithOddNReturns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/-2.5");
        act.then().statusCode(200);
    }
}