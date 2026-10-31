package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Assert;

public class CalcTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiReturnsOkStatus() {
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/1/2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/pi/0/0");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testEConstantStatusOk() {
        given().when().get("/api/calc/subtract/10/5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/2/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/10/2").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/e/0/0");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testSqrtStatusOk() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/2.718281828/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/sqrt/16/0");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPlusStatusOk() {
        given().when().get("/api/calc/sine/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/1/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/plus/15.5/4.5");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDivideStatusOk() {
        given().when().get("/api/calc/subtract/5/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/7/6").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/divide/10/2");
        Assert.assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testMultiplyStatusOk() {
        given().when().get("/api/calc/log/10/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0.5/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/multiply/3/4");
        Assert.assertEquals(200, act.getStatusCode());
    }
}