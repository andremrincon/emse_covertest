package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiConstantAndUnaries() {
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/2.718281828459045/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/3.1415926535/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/pi/0/0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAdditionBodyEquals() {
        given().when().get("/api/calc/multiply/2/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/10/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/9/3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/plus/15.5/4.5");
        resp.then().body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testDivideProducesExpectedResult() {
        given().when().get("/api/calc/plus/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/5/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/divide/9/3");
        resp.then().body(equalTo("3.0"));
    }
}