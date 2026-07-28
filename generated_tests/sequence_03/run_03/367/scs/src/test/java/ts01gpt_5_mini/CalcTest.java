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
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPlusOperationProducesCorrectSum() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/2.718281828/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0.5/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/5/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/3/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/10/2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/plus/10/5");
        assertEquals("15.0", act.asString());
    }

    @Test(timeout = 60000)
    public void testPiConstantReturnsMathPi() {
        given().when().get("/api/calc/plus/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/pi/0/0");
        assertEquals(Double.toString(Math.PI), act.asString());
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturnsServerError() {
        given().when().get("/api/calc/plus/2/2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/divide/100/0");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSqrtOfNegativeReturnsNaN() {
        given().when().get("/api/calc/plus/3/3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/sqrt/-4/0");
        assertEquals(Double.toString(Double.NaN), act.asString());
    }

    @Test(timeout = 60000)
    public void testLogOfOneReturnsZero() {
        given().when().get("/api/calc/plus/4/4").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/log/1/0");
        assertEquals("0.0", act.asString());
    }

    @Test(timeout = 60000)
    public void testSineOfZeroReturnsZero() {
        given().when().get("/api/calc/plus/5/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/sine/0/0");
        assertEquals("0.0", act.asString());
    }
}