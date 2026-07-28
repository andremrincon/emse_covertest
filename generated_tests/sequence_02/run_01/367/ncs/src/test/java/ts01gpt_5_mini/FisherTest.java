package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
            if (base == null || base.isEmpty()) {
                base = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_fisher_a1_b1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 1, 1, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_fisher_a1_bNot1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 1, 4, 0.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_fisher_aNot1_b1_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_fisher_aNot1_bNot1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 4, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_fisher_invalid_x_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 1.2);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_fisher_large_odd_parameters_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 11, 7, 0.75);
        resp.then().statusCode(200);
    }
}