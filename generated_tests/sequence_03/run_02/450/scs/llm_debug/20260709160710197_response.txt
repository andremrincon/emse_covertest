package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiReturnsOk() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 9, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", 2.718281828459045, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "cosine", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "tangent", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 1, 2).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", 5, 3).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 2, 3).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", 10, 2).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", 0, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEReturnsOk() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 4, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 3, 7).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", 0, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtReturnsOk() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 10, 5).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", 8, 2).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 16, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLogReturnsOk() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", 1, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "cosine", 1, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", 10, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPlusReturnsOk() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 6, 7).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", 20, 5).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 15.5, 4.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideReturnsOk() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 3, 5).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", 20, 4).then().statusCode(200);
    }
}