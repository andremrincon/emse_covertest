package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setUp() {
        String env = System.getProperty("API_BASE");
        if (env == null || env.isEmpty()) {
            env = System.getenv().getOrDefault("API_BASE", "http://localhost:8080");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionBranch() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesBranch() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/0.1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNZero() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/0/0.1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroWithNGreaterThanOne() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/2/0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidNegativeN() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/-1/1");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroWithNZeroOrOneIsBad() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/1/0");
        resp.then().statusCode(400);
    }
}