package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testConstantOperators() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnaryMathOperators() {
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/2.718281828459045/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTrigOperators() {
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryArithmeticOperators() {
        given().when().get("/api/calc/plus/5/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/10/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/6/7").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideOperator() {
        given().when().get("/api/calc/divide/20/4").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownOperator() {
        given().when().get("/api/calc/unknown/5/3").then().statusCode(200);
    }
}