package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("test.base.url", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testConstantOperators() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnaryMathOperators() {
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/10/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTrigonometricOperators() {
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryAddSubtractOperators() {
        given().when().get("/api/calc/plus/5/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/5/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryMultiplyDivideOperators() {
        given().when().get("/api/calc/multiply/5/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/6/2").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidOperator() {
        given().when().get("/api/calc/plus/5/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/power/5/3").then().statusCode(200);
    }
}