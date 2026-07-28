package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE_URL", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeX_ShouldReturn400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/-1.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NIsZero_ShouldReturn200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/0/1.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XZero_NGreaterThanOne_ShouldReturn200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XGreaterThanOne_ContinuedFractionPath_ShouldReturn200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesWithPsiComputation_n2_xSmall_ShouldReturn200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/2/0.1");
        resp.then().statusCode(200);
    }
}