package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CalcTest {

    @BeforeClass
    public static void initBaseUri() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiReturnsMathPi() {
        given().when().get("/api/pat/The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/pi/0.0/0.0");
        assertEquals(Double.toString(Math.PI), resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPlusAddsTwoDoubles() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/plus/15.5/4.5");
        assertEquals("20.0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSqrtUnaryOperation() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/sqrt/16.0/0.0");
        assertEquals("4.0", resp.getBody().asString());
    }
}