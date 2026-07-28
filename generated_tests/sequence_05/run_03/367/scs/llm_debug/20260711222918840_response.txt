package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {
    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiEndpointReturnsOk() {
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/10/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/pi/0/0");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDivideReturnsCorrectBody() {
        given().when().get("/api/calc/multiply/2/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/10/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/divide/10/2");
        Assert.assertEquals("5.0", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPlusReturnsStatusOk() {
        given().when().get("/api/calc/log/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/4/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/plus/15.5/4.5");
        Assert.assertEquals(200, act.getStatusCode());
    }
}