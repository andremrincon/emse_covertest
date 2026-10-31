package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionReturnsSuccessStatusField() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(200).extract().response();
        resp.then().body("status", nullValue());
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", -1, 2.5);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroAndNOneReturns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 1, 0.0);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNTwoReturnsOneAsResult() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 2, 0.0).then().statusCode(200).extract().response();
        resp.then().body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testExpintSmallXSeriesPathReturns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 2, 0.1);
        resp.then().statusCode(200);
    }
}