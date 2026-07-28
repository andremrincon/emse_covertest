package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCalcPiReturns200() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", unique).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcSqrtReturns200() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", unique).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9", "0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcDivideByZeroReturns500() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", unique).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "100", "0");
        resp.then().statusCode(200);
    }
}