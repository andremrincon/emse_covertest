package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
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
    public void test_bessj_n_less_than_two_returns_400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/1/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_bessj_x_zero_returns_200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_bessj_ax_greater_than_n_uses_bessj1_asymptotic_branch() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/2/9.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_bessj_ax_small_triggers_backward_recurrence_and_scaling() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/1e-10").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_bessj_negative_x_with_odd_n_returns_success() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/-5.0").then().statusCode(200);
    }
}