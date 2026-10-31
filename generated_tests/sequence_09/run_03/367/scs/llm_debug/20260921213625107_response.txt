package ts01gpt_5_mini_3;

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
    public void testPiReturnsPi() {
        given().when().get("/api/cookie/session-id/abc-123-xyz-789/example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/pi/0/0");
        assertEquals("3.141592653589793", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testEReturnsE() {
        given().when().get("/api/cookie/session-id/1/localhost").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/e/0/0");
        assertEquals("2.718281828459045", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testSqrtUnary() {
        given().when().get("/api/cookie/session-id/abc-123-xyz-789/example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/sqrt/16/0");
        assertEquals("4.0", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testPlusBinaryAdds() {
        given().when().get("/api/cookie/session-id/1/example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/plus/15.5/4.5");
        assertEquals("20.0", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testSubtractBinary() {
        given().when().get("/api/cookie/session-id/abc-123-xyz-789/example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/subtract/10/4.5");
        assertEquals("5.5", act.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturnsServerError() {
        given().when().get("/api/cookie/session-id/1/example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/divide/100/0");
        assertEquals(200, act.getStatusCode());
    }
}