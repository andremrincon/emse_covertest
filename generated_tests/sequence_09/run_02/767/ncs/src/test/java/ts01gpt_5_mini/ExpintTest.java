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
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_continuedFraction_branch_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_series_branch_smallX_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/0.1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_nEqualsZero_formulaPath_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/0/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_xZero_nGreaterThanOne_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_xZero_nEqualsOne_throwsBadRequest_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/1/0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_negativeN_invalidParameter_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/-1/2.5");
        act.then().statusCode(400);
    }
}