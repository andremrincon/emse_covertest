package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String base = Optional.ofNullable(System.getProperty("baseUrl"))
                .orElseGet(() -> Optional.ofNullable(System.getenv("BASE_URL")).orElse("http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_bessj_n_less_than_2_returns_400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/1/2.5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_bessj_x_zero_returns_200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_bessj_forward_recurrence_when_ax_greater_than_n_returns_200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/5.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_bessj_backward_recurrence_with_small_x_returns_200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/10/1e-10");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_bessj_negative_x_with_odd_n_returns_200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/-5.0");
        resp.then().statusCode(200);
    }
}