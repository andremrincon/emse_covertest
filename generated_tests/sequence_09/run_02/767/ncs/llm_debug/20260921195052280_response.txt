package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.RestAssured;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInvalidNReturns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", -5, 2.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testAxZeroReturns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 0.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAxGreaterThanNForwardRecurrenceReturns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 5.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAxLessOrEqualNBackwardRecurrenceReturns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 5, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeXOddNSignFlipReturns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, -2.5).then().statusCode(200);
    }
}