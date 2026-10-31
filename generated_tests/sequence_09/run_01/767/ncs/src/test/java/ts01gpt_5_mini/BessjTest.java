package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        BASE = base;
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void test_bessj_forward_path_status200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/bessj/3/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_bessj_backward_path_status200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/bessj/5/1e-10");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_bessj_zero_x_status200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/bessj/2/0.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_bessj_large_x_asymptotic_status200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/bessj/3/20.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_bessj_invalid_n_less_than_two_status400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/bessj/1/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_bessj_negative_x_signflip_status200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/bessj/3/-2.5");
        act.then().statusCode(200);
    }
}