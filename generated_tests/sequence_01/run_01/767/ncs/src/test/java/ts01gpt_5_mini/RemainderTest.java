package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String url = System.getenv("BASE_URL");
        if (url == null || url.isEmpty()) url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) url = "http://localhost:8080";
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testPositiveDividendPositiveDivisorReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPositiveDividendNegativeDivisorReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 17, -9);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNegativeDividendPositiveDivisorReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", -17, 5);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNegativeDividendNegativeDivisorReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", -17, -5);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testZeroDividendReturns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 0, 5);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testZeroDivisorReturns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 5, 0);
        assertEquals(200, act.getStatusCode());
    }
}