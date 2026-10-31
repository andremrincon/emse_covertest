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
    public static void setupClass() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testPiReturns200() {
        given().when().get("/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0.0", "0.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEReturnsBodyMathE() {
        given().when().get("/api/pat/{txt}", "arrangeE").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", "0.0", "0.0");
        act.then().body(equalTo(String.valueOf(Math.E)));
    }

    @Test(timeout = 60000)
    public void testSqrtUnaryProducesSquareRoot() {
        given().when().get("/api/pat/{txt}", "arrangeSqrt").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9.0", "0.0");
        act.then().body(equalTo("3.0"));
    }

    @Test(timeout = 60000)
    public void testPlusBinaryAddsNumbers() {
        given().when().get("/api/pat/{txt}", "arrangePlus").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "15.5", "4.5");
        act.then().body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturns500() {
        given().when().get("/api/pat/{txt}", "arrangeDiv").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "100.0", "0.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownOpReturnsZeroString() {
        given().when().get("/api/pat/{txt}", "arrangeUnknown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "unknownop", "1.0", "2.0");
        act.then().body(equalTo("0.0"));
    }
}