package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CalcTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCalcPi() {
        given().when().get("/api/calc/sqrt/16.0/0.0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/pi/15.5/4.5");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcE() {
        given().when().get("/api/calc/log/10.0/0.0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/e/15.5/4.5");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcSine() {
        given().when().get("/api/calc/cosine/0.0/0.0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0.0/0.0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/sine/0.0/0.0");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcPlus() {
        given().when().get("/api/calc/subtract/15.5/4.5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/plus/15.5/4.5");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcMultiply() {
        given().when().get("/api/calc/divide/15.5/4.5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/calc/multiply/15.5/4.5");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcUnknownOp() {
        Response response = given().when().get("/api/calc/unknown/15.5/4.5");
        response.then().statusCode(200);
    }
}