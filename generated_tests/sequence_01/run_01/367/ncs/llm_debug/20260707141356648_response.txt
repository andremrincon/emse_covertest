package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionPathReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesPathForNOneReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/1/0.1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/-1/1.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNZeroPositiveXReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/0/1.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXWithZeroNReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/0/0");
        resp.then().statusCode(400);
    }
}