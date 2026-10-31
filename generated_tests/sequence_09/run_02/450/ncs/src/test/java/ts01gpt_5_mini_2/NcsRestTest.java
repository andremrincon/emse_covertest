package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void bessj_valid_n_returns_200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 3, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_n_too_small_returns_400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 2, 2.5);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_n_too_large_returns_400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/{n}/{x}", 1001, 2.5);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisher_valid_parameters_return_200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fisher_m_too_large_returns_400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 1001, 5, 0.75);
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void remainder_valid_within_limits_returns_200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainder_out_of_limits_returns_400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 10001, 1);
        act.then().statusCode(400);
    }
}