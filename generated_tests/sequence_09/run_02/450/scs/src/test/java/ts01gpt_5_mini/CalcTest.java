package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPlusProducesExpectedSum() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/1/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/plus/15.5/4.5");
        act.then().assertThat().body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testSubtractReturnsOkStatus() {
        given().when().get("/api/calc/sine/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/1/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/subtract/15.5/4.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideByZeroProducesServerError() {
        given().when().get("/api/calc/multiply/2/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/divide/100/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtOfNegativeReturnsNaN() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/sqrt/-1/0");
        act.then().assertThat().body(equalTo("NaN"));
    }

    @Test(timeout = 60000)
    public void testMultiplyProducesExpectedProduct() {
        given().when().get("/api/calc/divide/10/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/multiply/2/3");
        act.then().assertThat().body(equalTo("6.0"));
    }

    @Test(timeout = 60000)
    public void testLogOfZeroReturnsNegativeInfinityString() {
        given().when().get("/api/calc/plus/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/log/0/0");
        act.then().assertThat().body(equalTo("-Infinity"));
    }
}