package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testConstantOperatorsPiAndE() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnaryOperatorsSqrtAndLog() {
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/1/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnaryOperatorsSineAndCosine() {
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnaryTangentAndBinaryPlus() {
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/10/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinarySubtractAndMultiply() {
        given().when().get("/api/calc/subtract/10/5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/10/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryDivideAndUnknownOperator() {
        given().when().get("/api/calc/divide/10/5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/unknownop/1/2").then().statusCode(200);
    }
}