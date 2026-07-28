package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testPiConstantOperator() {
        given().when().get("/api/calc/e/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/calc/pi/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtUnaryOperator() {
        given().when().get("/api/calc/log/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/16/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSineTrigOperator() {
        given().when().get("/api/calc/cosine/0/1").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/1").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPlusBinaryOperator() {
        given().when().get("/api/calc/subtract/10/5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/15.5/4.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMultiplyBinaryOperator() {
        given().when().get("/api/calc/divide/10/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/3/4").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownOperatorReturnsDefault() {
        given().when().get("/api/calc/unknown/1/1").then().statusCode(200);
    }
}