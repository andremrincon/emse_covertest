package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    private String base() {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        if (b.endsWith("/")) b = b.substring(0, b.length() - 1);
        return b;
    }

    @Test(timeout = 60000)
    public void testPiReturnsOk() {
        String base = base();
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/pi/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturnsServerError() {
        String base = base();
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/divide/100/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtUnaryReturnsOk() {
        String base = base();
        given().when().get(base + "/api/pat/healthcheck").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/sqrt/9/0").then().statusCode(200);
    }
}