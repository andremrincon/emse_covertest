package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiReturnsMathPi() {
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/pi/0/0").then().body(equalTo(String.valueOf(Math.PI)));
    }

    @Test(timeout = 60000)
    public void testEReturnsMathE() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().body(equalTo(String.valueOf(Math.E)));
    }

    @Test(timeout = 60000)
    public void testSqrtUnaryOperator() {
        given().when().get("/api/calc/log/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().body(equalTo("3.0"));
    }

    @Test(timeout = 60000)
    public void testPlusBinaryOperator() {
        given().when().get("/api/calc/subtract/5/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/2/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/9/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/15.5/4.5").then().body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testSubtractBinaryOperator() {
        given().when().get("/api/calc/plus/2/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/3/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/8/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/10/4").then().body(equalTo("6.0"));
    }

    @Test(timeout = 60000)
    public void testMultiplyBinaryOperator() {
        given().when().get("/api/calc/plus/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/7/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/12/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/2/5").then().body(equalTo("10.0"));
    }
}