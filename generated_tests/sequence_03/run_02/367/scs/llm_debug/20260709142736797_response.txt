package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.Assert;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    private static String base() {
        String env = System.getenv("API_BASE");
        return System.getProperty("api.base", env != null ? env : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPiConstant() {
        String base = base();
        given().when().get(base + "/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/sqrt/49/0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/log/2.718281828459045/0").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/PI/0/0");
        Assert.assertEquals("" + Math.PI, act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testEConstant() {
        String base = base();
        given().when().get(base + "/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/e/0/0");
        Assert.assertEquals("" + Math.E, act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSineZero() {
        String base = base();
        given().when().get(base + "/api/calc/plus/1/2").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/subtract/5/3").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/multiply/2/3").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/sine/0/0");
        Assert.assertEquals("" + Math.sin(0.0), act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPlusOperation() {
        String base = base();
        given().when().get(base + "/api/calc/subtract/10/4").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/multiply/3/3").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/divide/9/3").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/plus/15.5/4.5");
        Assert.assertEquals("20.0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testDivideOperation() {
        String base = base();
        given().when().get(base + "/api/calc/plus/2/2").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/subtract/7/1").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/multiply/4/2").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/divide/9/3");
        Assert.assertEquals("3.0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testMultiplyOperation() {
        String base = base();
        given().when().get(base + "/api/calc/subtract/8/2").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/log/1/0").then().statusCode(lessThan(300));
        given().when().get(base + "/api/calc/sqrt/4/0").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/calc/multiply/3/2.5");
        Assert.assertEquals("7.5", act.getBody().asString());
    }
}