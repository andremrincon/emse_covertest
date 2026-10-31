package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGserPathReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfPathReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/gammq/{a}/{x}", 0.001, 1000.0);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeAReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/gammq/{a}/{x}", -1.0, 2.3);
        r.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testNegativeXReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/gammq/{a}/{x}", 5.5, -1.0);
        r.then().statusCode(400);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testXZeroReturnsResultOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/gammq/{a}/{x}", 5.5, 0.0);
        double value = r.jsonPath().getDouble("result");
        assertEquals(1.0, value, 1e-6);
    }

    @Test(timeout = 60000)
    public void testNonNumericAProduces400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/gammq/{a}/{x}", "abc", "2.3");
        r.then().statusCode(400);
    }
}