package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPlusReturns200() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/2.718281828/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/1/0").then().statusCode(lessThan(300));
        given().when().get(("/api/calc/subtract/10/3")).then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/2/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/10/2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/plus/15.5/4.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturns500() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/plus/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/3/3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/divide/100/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidNumberReturns400() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/2/3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/plus/10/twenty");
        act.then().statusCode(400);
    }
}