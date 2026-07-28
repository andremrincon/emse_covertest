package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCalcConstants() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));

        given().pathParam("op", "pi").pathParam("arg1", 0).pathParam("arg2", 0).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
        given().pathParam("op", "e").pathParam("arg1", 0).pathParam("arg2", 0).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcUnary() {
        given().when().get("/api/calc/sqrt/4/0").then().statusCode(lessThan(300));

        given().pathParam("op", "sqrt").pathParam("arg1", 4).pathParam("arg2", 0).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
        given().pathParam("op", "log").pathParam("arg1", 10).pathParam("arg2", 0).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
        given().pathParam("op", "sine").pathParam("arg1", 0).pathParam("arg2", 0).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
        given().pathParam("op", "cosine").pathParam("arg1", 0).pathParam("arg2", 0).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
        given().pathParam("op", "tangent").pathParam("arg1", 0).pathParam("arg2", 0).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcBinary() {
        given().when().get("/api/calc/plus/1/1").then().statusCode(lessThan(300));

        given().pathParam("op", "plus").pathParam("arg1", 5).pathParam("arg2", 3).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
        given().pathParam("op", "subtract").pathParam("arg1", 5).pathParam("arg2", 3).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
        given().pathParam("op", "multiply").pathParam("arg1", 5).pathParam("arg2", 3).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
        given().pathParam("op", "divide").pathParam("arg1", 6).pathParam("arg2", 3).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcInvalidOperator() {
        given().when().get("/api/calc/plus/1/1").then().statusCode(lessThan(300));

        given().pathParam("op", "power").pathParam("arg1", 2).pathParam("arg2", 3).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcInvalidNumberFormat() {
        given().when().get("/api/calc/plus/1/1").then().statusCode(lessThan(300));

        given().pathParam("op", "plus").pathParam("arg1", "twenty").pathParam("arg2", 5).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testCalcDivideByZero() {
        given().when().get("/api/calc/plus/1/1").then().statusCode(lessThan(300));

        given().pathParam("op", "divide").pathParam("arg1", 10).pathParam("arg2", 0).when().get("/api/calc/{op}/{arg1}/{arg2}").then().statusCode(200);
    }
}