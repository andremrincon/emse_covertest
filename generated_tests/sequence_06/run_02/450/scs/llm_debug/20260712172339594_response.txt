package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testConstantOperators() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().body(containsString("2.71828"));
    }

    @Test(timeout = 60000)
    public void testUnaryOperators() {
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().body(containsString("0.0"));
    }

    @Test(timeout = 60000)
    public void testBinaryOperators() {
        given().when().get("/api/calc/plus/5/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/10/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/3/7").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/20/4").then().body(containsString("5.0"));
    }

    @Test(timeout = 60000)
    public void testUnknownOperator() {
        given().when().get("/api/calc/unknown/5/3").then().body(containsString("0.0"));
    }

    @Test(timeout = 60000)
    public void testDivideByZero() {
        given().when().get("/api/calc/divide/100/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidNumberFormat() {
        given().when().get("/api/calc/plus/10/twenty").then().statusCode(400);
    }
}