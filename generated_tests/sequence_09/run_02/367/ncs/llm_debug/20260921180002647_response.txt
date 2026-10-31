package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpintNegativeN() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/-1/1.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNZeroReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/0/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroAndNOneBadRequest() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/1/0.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNGreaterReturns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/2/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionPath() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesPathNm1Zero() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/1/0.1");
        resp.then().statusCode(200);
    }
}