package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE") != null ? System.getenv("API_BASE") : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiConstantReturnsPiValue() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0");
        assertEquals("3.141592653589793", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSqrtUnaryReturnsSquareRoot() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9", "0");
        assertEquals("3.0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPlusBinaryAddsNumbers() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "15.5", "4.5");
        assertEquals("20.0", resp.getBody().asString());
    }
}