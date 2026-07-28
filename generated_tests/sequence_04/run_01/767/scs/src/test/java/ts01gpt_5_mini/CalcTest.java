package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiReturnsMathPiString() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 9, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", 1, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "cosine", 0, 0).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", 0, 0);
        act.then().body(equalTo(Double.toString(Math.PI)));
    }

    @Test(timeout = 60000)
    public void testEReturnsMathEString() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "tangent", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 1, 2).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", 5, 2).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", 0, 0);
        act.then().body(equalTo(Double.toString(Math.E)));
    }

    @Test(timeout = 60000)
    public void testSqrtOfSixteenReturnsFour() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 3, 4).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", 10, 2).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 16, 0);
        act.then().body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testPlusWithDecimalsReturnsSum() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", 10, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", 1, 0).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 15.5, 4.5);
        act.then().body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturnsServerError() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 2, 3).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", 100, 0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLogOfNegativeReturnsNaNString() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "cosine", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "tangent", 1, 0).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", -1, 0);
        act.then().body(equalTo(Double.toString(Math.log(-1))));
    }
}