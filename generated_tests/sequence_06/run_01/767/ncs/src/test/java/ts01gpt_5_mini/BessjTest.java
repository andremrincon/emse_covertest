package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_bessj_n_less_than_two_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 1, 2.5);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_bessj_x_zero_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 0.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_bessj_ax_greater_than_n_forward_recursion_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 10.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_bessj_ax_less_or_equal_n_backward_recursion_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 20, 1.0E-10);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_bessj_negative_x_odd_n_sign_flip_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, -2.5);
        resp.then().statusCode(200);
    }
}