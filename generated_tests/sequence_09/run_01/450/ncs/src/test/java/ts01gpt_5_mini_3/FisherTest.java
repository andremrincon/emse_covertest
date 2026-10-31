package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("api.base");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("API_BASE");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void testFisher_whenAEquals1AndBEquals1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/1/1/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_whenAEquals1AndBNot1_loopsAndReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/1/4/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_whenANot1AndBEquals1_executesAlternatePath_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/1/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_whenANot1AndBNot1_complexPath_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/6/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_withInvalidM_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/abc/5/0.75");
        act.then().statusCode(400);
    }
}