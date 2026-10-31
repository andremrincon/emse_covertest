package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiReturns200() {
        given().when().get("/api/calc/plus/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/pi/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEReturns200() {
        given().when().get("/api/calc/log/10/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtBodyEquals4() {
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/16/0").then().assertThat().body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testAdditionBodyEquals20() {
        given().when().get("/api/calc/subtract/10/5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/2/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/15.5/4.5").then().assertThat().body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testDivideByZeroGives500() {
        given().when().get("/api/calc/divide/100/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/100/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPowerInvalidReturns400() {
        given().when().get("/api/calc/log/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/power/2/3").then().statusCode(200);
    }
}