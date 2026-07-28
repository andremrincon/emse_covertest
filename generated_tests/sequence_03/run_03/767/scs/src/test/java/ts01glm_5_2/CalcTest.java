package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

public class CalcTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPiOperator() {
        given().when().get("/api/calc/log/10/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/pi/0/0").then().statusCode(200).body(containsString("3.141592653589793"));
    }

    @Test(timeout = 60000)
    public void testEOperator() {
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(200).body(containsString("2.718281828459045"));
    }

    @Test(timeout = 60000)
    public void testSqrtOperator() {
        given().when().get("/api/calc/subtract/10/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(200).body(containsString("4.0"));
    }

    @Test(timeout = 60000)
    public void testPlusOperator() {
        given().when().get("/api/calc/plus/15.5/4.5").then().statusCode(200).body(containsString("20.0"));
    }

    @Test(timeout = 60000)
    public void testDivideOperator() {
        given().when().get("/api/calc/divide/100/4").then().statusCode(200).body(containsString("25.0"));
    }

    @Test(timeout = 60000)
    public void testUnknownOperatorReturnsZero() {
        given().when().get("/api/calc/unknown/5/3").then().statusCode(200).body(containsString("0.0"));
    }
}