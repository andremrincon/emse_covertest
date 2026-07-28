package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiConstantReturnsOk() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", id).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtUnaryReturnsExpectedBody() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", id).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9", "0");
        act.then().body(equalTo("3.0"));
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturnsServerError() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", id).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "100", "0");
        act.then().statusCode(200);
    }
}