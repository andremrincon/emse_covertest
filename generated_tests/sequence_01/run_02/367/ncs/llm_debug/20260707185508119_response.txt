package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContinuedFractionPathReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeriesPathReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/0.1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNZeroCaseReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/0/1.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testXZeroWithNGreaterThanOneReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/2/0.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testXZeroWithNZeroOrOneProducesBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/1/0.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testNegativeXProducesBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/-1.0");
        act.then().statusCode(400);
    }
}