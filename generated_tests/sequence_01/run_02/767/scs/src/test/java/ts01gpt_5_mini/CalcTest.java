package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            String e2 = System.getenv("API_BASE");
            env = (e2 != null && !e2.isEmpty()) ? e2 : "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testPiConstant() {
        given().when().get("/api/pat/HealthCheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/pi/0/0");
        assertEquals("3.141592653589793", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSqrtOfNine() {
        given().when().get("/api/pat/HealthCheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/sqrt/9/0");
        assertEquals("3.0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSineOfPiOverTwo() {
        given().when().get("/api/pat/HealthCheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/sine/1.5707963267948966/0");
        assertEquals("1.0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testAdditionProducesTwenty() {
        given().when().get("/api/pat/HealthCheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/plus/15.5/4.5");
        assertEquals("20.0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSubtractionProducesSeven() {
        given().when().get("/api/pat/HealthCheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/subtract/10/3");
        assertEquals("7.0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturnsServerError() {
        given().when().get("/api/pat/HealthCheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/divide/100/0");
        assertEquals(200, resp.statusCode());
    }
}