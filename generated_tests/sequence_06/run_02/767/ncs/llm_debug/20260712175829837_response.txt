package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN_Returns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", -1, 1.0);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_XZeroWithNZero_Returns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 0, 0.0);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NIsZero_Returns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 0, 1.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_ContinuedFractionBranch_Returns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesNm1Zero_BodyContainsResult() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 1, 0.1);
        resp.then().statusCode(200).body("$", notNullValue());
    }
}