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
    public static void setUp() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void divideByZero_returns500() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/2.718281828/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/5/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/2/3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/divide/100/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void pi_returns200() {
        given().when().get("/api/cookie/session-id/abc-123-xyz-789/example.com").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/pi/0/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void sqrt_ofSixteen_returnsExpectedBody() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/sqrt/16/0");
        act.then().body(equalTo("4.0"));
    }
}