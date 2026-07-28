package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPiConstant() {
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/pi/0/0");
        response.then().statusCode(200).body(equalTo("3.141592653589793"));
    }

    @Test(timeout = 60000)
    public void testSqrtUnary() {
        given().when().get("/api/calc/log/10/0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/sqrt/16/0");
        response.then().statusCode(200).body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testSineTrig() {
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/sine/0/0");
        response.then().statusCode(200).body(equalTo("0.0"));
    }

    @Test(timeout = 60000)
    public void testTangentTrig() {
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/plus/5/3");
        response.then().statusCode(200).body(equalTo("8.0"));
    }

    @Test(timeout = 60000)
    public void testSubtractBinary() {
        given().when().get("/api/calc/multiply/4/5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/subtract/10/3");
        response.then().statusCode(200).body(equalTo("7.0"));
    }

    @Test(timeout = 60000)
    public void testDivideBinary() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/divide/10/2");
        response.then().statusCode(200).body(equalTo("5.0"));
    }
}