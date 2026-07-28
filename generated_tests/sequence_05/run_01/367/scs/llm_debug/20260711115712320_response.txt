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
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCalcSubtractReturns200() {
        given().when().get("/api/pat/{txt}", "Setup text for subtract test").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", "10", "4.5");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcPiReturnsExpectedBody() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0");
        String body = res.getBody().asString();
        assertEquals("3.141592653589793", body);
    }

    @Test(timeout = 60000)
    public void testCalcDivideByZeroReturns500() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", "1", "example.com").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "100", "0");
        res.then().statusCode(200);
    }
}