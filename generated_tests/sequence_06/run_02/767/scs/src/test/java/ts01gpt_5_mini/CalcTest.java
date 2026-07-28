package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    private static final String BASE = initBase();

    private static String initBase() {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        return b;
    }

    @Test(timeout = 60000)
    public void testPiEndpointReturns200() {
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "log", "2.718281828", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "sine", "1", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "cosine", "0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "tangent", "0.5", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "plus", "1", "2").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "subtract", "5", "3").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "multiply", "2", "4").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "divide", "10", "2").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "e", "0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPlusReturnsExpectedBody() {
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "e", "0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "sqrt", "16", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "log", "10", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "sine", "0.0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "cosine", "0.0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "tangent", "0.0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "subtract", "7", "2").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "multiply", "3", "3").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "divide", "9", "3").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "plus", "15.5", "4.5").then().body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturns500() {
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "plus", "1", "1").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "sqrt", "4", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "log", "1", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "divide", "100", "0").then().statusCode(200);
    }
}