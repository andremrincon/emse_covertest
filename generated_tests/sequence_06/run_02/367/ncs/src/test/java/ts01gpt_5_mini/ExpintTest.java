package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testInvalidNegativeNProduces400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", -1, 1.0);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testXZeroWithNOneProduces400() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 1, 0.0);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testNZeroBranchReturns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 0, 2.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testXZeroWithNGreaterThanOneReturns200() {
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 2, 0.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testContinuedFractionPathForXGreaterThanOneReturns200() {
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeriesPathForNOneAndSmallXReturns200() {
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 1, 0.1);
        resp.then().statusCode(200);
    }
}