package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    private String base() {
        String b = System.getProperty("baseUrl");
        if (b != null && !b.isEmpty()) return b;
        b = System.getenv("BASE_URL");
        if (b != null && !b.isEmpty()) return b;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPiOperation() {
        String base = base();
        given().when().get(base + "/api/calc/e/0.0/0.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/sqrt/4.0/0.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/log/10.0/0.0").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/pi/0.0/0.0");
        act.then().body(equalTo("3.141592653589793"));
    }

    @Test(timeout = 60000)
    public void testEOperation() {
        String base = base();
        given().when().get(base + "/api/calc/sine/1.0/0.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/cosine/1.0/0.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/tangent/1.0/0.0").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/e/0.0/0.0");
        act.then().body(equalTo("2.718281828459045"));
    }

    @Test(timeout = 60000)
    public void testSqrtOperation() {
        String base = base();
        given().when().get(base + "/api/calc/plus/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/subtract/10.0/2.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/multiply/2.0/3.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/divide/100.0/2.0").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/sqrt/9.0/0.0");
        act.then().body(equalTo("3.0"));
    }

    @Test(timeout = 60000)
    public void testPlusOperation() {
        String base = base();
        given().when().get(base + "/api/calc/pi/0.0/0.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/e/0.0/0.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/log/2.718281828459045/0.0").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/plus/15.5/4.5");
        act.then().body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testDivideOperation() {
        String base = base();
        given().when().get(base + "/api/calc/plus/1.0/2.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/subtract/5.0/3.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/multiply/6.0/7.0").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/divide/100.0/4.0");
        act.then().body(equalTo("25.0"));
    }

    @Test(timeout = 60000)
    public void testSineOperation() {
        String base = base();
        given().when().get(base + "/api/calc/cosine/0.0/0.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/tangent/0.0/0.0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/log/1.0/0.0").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/sine/0.0/0.0");
        act.then().body(equalTo("0.0"));
    }
}