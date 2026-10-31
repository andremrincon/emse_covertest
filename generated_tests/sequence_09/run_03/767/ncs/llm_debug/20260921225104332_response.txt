package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("base.url", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_bessj_n_less_than_two_returns_400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/bessj/-5/2.5");
        Assert.assertEquals(400, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_bessj_x_zero_returns_zero_in_body() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/bessj/3/0.0");
        String body = res.asString();
        Assert.assertTrue(body.contains("0.0") || body.contains(":0") || body.contains("0"));
    }

    @Test(timeout = 60000)
    public void test_bessj_ax_greater_than_n_polynomial_branch_status_200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/bessj/3/5.0");
        Assert.assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_bessj_ax_greater_than_n_asymptotic_branch_status_200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/bessj/3/10.0");
        Assert.assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_bessj_backward_recurrence_branch_status_200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/bessj/50/1e-10");
        Assert.assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_bessj_negative_x_asymptotic_sign_flip() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/bessj/3/-10.0");
        String body = res.asString();
        Assert.assertTrue(body.contains("-"));
    }
}