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
        RestAssured.baseURI = System.getProperty("BASE_URL", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testPiReturnsMathPi() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "16", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", "2.718281828459045", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", "0.0", "0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0");
        assertEquals(Double.toString(Math.PI), act.asString());
    }

    @Test(timeout = 60000)
    public void testSqrtComputesSquareRoot() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "1", "2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", "5", "3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", "2", "3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "10", "2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9", "0");
        assertEquals(Double.toString(Math.sqrt(9.0)), act.asString());
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturnsServerError() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", "0", "0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "100", "0");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testMultiplyReturnsProduct() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", "10", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", "1", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "cosine", "1", "0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", "2.5", "4");
        assertEquals(Double.toString(2.5 * 4.0), act.asString());
    }

    @Test(timeout = 60000)
    public void testSineIsCaseInsensitive() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "COSINE", "0.5", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "TANgent", "0.25", "0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "SInE", "1", "0");
        assertEquals(Double.toString(Math.sin(1.0)), act.asString());
    }

    @Test(timeout = 60000)
    public void testUnknownOpReturnsZeroString() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", "0", "0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "unknownop", "1", "2");
        assertEquals("0.0", act.asString());
    }
}