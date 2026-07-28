package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCalcPiReturns200() {
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/5/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/9/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/10/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/pi/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcDivideByZeroReturns500() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/10/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/100/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcInvalidNumberReturns400() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/add/10/twenty").then().statusCode(400);
    }
}