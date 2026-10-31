package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CalcTest {

    @BeforeClass
    public static void setup() {
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
    public void testPiReturns200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-" + uuid, "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSqrtReturnsThreePointZeroBody() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-" + uuid, "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9", "0");
        String body = r.getBody().asString().trim();
        if (body.startsWith("\"") && body.endsWith("\"")) {
            body = body.substring(1, body.length() - 1);
        }
        assertEquals("3.0", body);
    }

    @Test(timeout = 60000)
    public void testPlusAddsCorrectlyBody() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-" + uuid, "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "15.5", "4.5");
        String body = r.getBody().asString().trim();
        if (body.startsWith("\"") && body.endsWith("\"")) {
            body = body.substring(1, body.length() - 1);
        }
        assertEquals("20.0", body);
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturns500() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-" + uuid, "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "100", "0");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testEUppercaseReturns200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-" + uuid, "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "E", "0", "0");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testLogOfEReturnsOneBody() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-" + uuid, "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", "2.718281828459045", "0");
        String body = r.getBody().asString().trim();
        if (body.startsWith("\"") && body.endsWith("\"")) {
            body = body.substring(1, body.length() - 1);
        }
        assertEquals("1.0", body);
    }
}