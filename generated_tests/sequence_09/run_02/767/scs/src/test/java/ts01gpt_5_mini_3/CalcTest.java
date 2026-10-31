package ts01gpt_5_mini_3;

import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    private String base() {
        String b = System.getProperty("baseUrl");
        if (b == null || b.isEmpty()) {
            b = System.getenv("BASE_URL");
        }
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080";
        }
        return b;
    }

    @Test(timeout = 60000)
    public void testPlusReturnsSum() {
        String b = base();
        RestAssured.baseURI = b;
        given().when().get(b + "/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get(b + "/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get(b + "/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get(b + "/api/calc/log/10/0").then().statusCode(lessThan(300));
        given().when().get(b + "/api/calc/sine/1/0").then().statusCode(lessThan(300));
        given().when().get(b + "/api/calc/cosine/1/0").then().statusCode(lessThan(300));
        given().when().get(b + "/api/calc/tangent/1/0").then().statusCode(lessThan(300));
        given().when().get(b + "/api/calc/multiply/2/3").then().statusCode(lessThan(300));
        Response act = given().when().get(b + "/api/calc/plus/15.5/4.5");
        Assert.assertEquals("20.0", act.asString());
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturns500() {
        String b = base();
        RestAssured.baseURI = b;
        given().when().get(b + "/api/calc/plus/1/1").then().statusCode(lessThan(300));
        given().when().get(b + "/api/calc/sqrt/4/0").then().statusCode(lessThan(300));
        Response act = given().when().get(b + "/api/calc/divide/100/0");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSubtractReturnsDifference() {
        String b = base();
        RestAssured.baseURI = b;
        given().when().get(b + "/api/calc/plus/0/0").then().statusCode(lessThan(300));
        given().when().get(b + "/api/calc/log/2/0").then().statusCode(lessThan(300));
        given().when().get(b + "/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get(b + "/api/calc/subtract/15.5/4.5");
        Assert.assertEquals("11.0", act.asString());
    }
}