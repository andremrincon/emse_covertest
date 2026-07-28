package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPiAndEAndSqrtOperators() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLogAndSineAndCosineOperators() {
        given().when().get("/api/calc/log/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTangentAndPlusOperators() {
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/5/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubtractOperator() {
        given().when().get("/api/calc/subtract/10/4").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMultiplyAndDivideOperators() {
        given().when().get("/api/calc/multiply/6/7").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/100/4").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownOperatorDefaultBranch() {
        given().when().get("/api/calc/unknownop/1/1").then().statusCode(200);
    }
}