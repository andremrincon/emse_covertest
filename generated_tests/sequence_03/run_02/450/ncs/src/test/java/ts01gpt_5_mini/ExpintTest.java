package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        RestAssured.baseURI = base != null ? base : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testNegativeXTriggersBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, -1).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testNZeroReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 0, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testXZeroWithNGreaterThanOneReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testXGreaterThanOneContinuedFraction200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testXLessOrEqualOneSeriesPath200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 0.1).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testXZeroWithNZeroBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 0, 0).then().statusCode(400);
    }
}