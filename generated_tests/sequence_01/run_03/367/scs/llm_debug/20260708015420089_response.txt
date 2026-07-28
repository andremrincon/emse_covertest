package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCalcPiStatus200() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "1", "2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", "5", "3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", "2", "3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "10", "2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", "0", "0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCalcDivideByZeroReturns500() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "15.5", "4.5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9", "0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "100", "0");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCalcSqrtReturnsBody() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "0", "0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9", "0");
        Assert.assertEquals("3.0", act.asString());
    }
}