package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testCalcPlus() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/plus/10/5");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcSqrt() {
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/sqrt/25/0");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcDivide() {
        given().when().get("/api/calc/log/10/0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/divide/10/2");
        response.then().statusCode(200);
    }
}