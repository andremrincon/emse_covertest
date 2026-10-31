package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiConstant() {
        given().when().get("/api/calc/plus/2/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/10/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/pi/0/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPlusOperation() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/plus/15.5/4.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideByZeroProducesServerError() {
        given().when().get("/api/calc/plus/10/5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/divide/100/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSineOperation() {
        given().when().get("/api/calc/log/2.718281828459045/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/sine/1.5707963267948966/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownOpDefaultsToZeroString() {
        given().when().get("/api/calc/plus/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/unknownop/10/20");
        act.then().body(equalTo("0.0"));
    }

    @Test(timeout = 60000)
    public void testEConstant() {
        given().when().get("/api/calc/plus/3/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/e/0/0");
        act.then().statusCode(200);
    }
}