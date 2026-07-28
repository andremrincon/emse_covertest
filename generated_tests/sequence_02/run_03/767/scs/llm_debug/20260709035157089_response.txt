package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCalcPlusOperationReturns200() {
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(lessThan(300));

        given().when().get("/api/calc/plus/15.5/4.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcDivideByZeroReturns500() {
        given().when().get("/api/calc/multiply/3/2").then().statusCode(lessThan(300));

        given().when().get("/api/calc/divide/100/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcPiConstantReturnsCorrectValue() {
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));

        given().when().get("/api/calc/pi/0/0").then().body(equalTo("3.141592653589793"));
    }
}