package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContinuedFraction_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        when().get("/api/expint/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeries_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        when().get("/api/expint/3/0.1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNZero_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        when().get("/api/expint/0/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testXZeroWithNGreaterThanOne_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        when().get("/api/expint/3/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeN_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        when().get("/api/expint/-1/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testXZeroWithNZeroOrOne_BadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        when().get("/api/expint/1/0.0").then().statusCode(400);
    }
}