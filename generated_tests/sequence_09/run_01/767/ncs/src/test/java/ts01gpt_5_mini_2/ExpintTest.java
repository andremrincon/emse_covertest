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
        RestAssured.baseURI = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testExpint_continuedFraction_branch_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_series_smallX_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/0.1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_nZero_case_returns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/0/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_xZero_nGreaterThanOne_returns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_negativeN_returns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/-1/2.5");
        resp.then().statusCode(400);
    }
}