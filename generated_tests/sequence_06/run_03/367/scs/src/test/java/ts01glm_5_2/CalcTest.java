package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CalcTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCalcConstantOperators_1() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcUnaryOperators_1() {
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/1/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcTrigOperators_1() {
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcBinaryAddSubtract_1() {
        given().when().get("/api/calc/plus/5/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/10/4").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcBinaryMultiplyDivide_1() {
        given().when().get("/api/calc/multiply/6/7").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/20/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcUnknownOperator_1() {
        given().when().get("/api/calc/unknown/1/1").then().statusCode(200);
    }
}