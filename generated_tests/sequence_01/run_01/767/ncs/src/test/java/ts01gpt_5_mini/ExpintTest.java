package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_continuedFraction_branch_returns200_for_xGreaterThanOne() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_series_branch_with_psi_returns200_for_n3_x0point1() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/0.1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_nEqualsZero_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/0/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_xEqualsZero_with_nGreaterThanOne_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_negativeN_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/-1/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_xZero_and_nZero_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/0/0");
        act.then().statusCode(400);
    }
}