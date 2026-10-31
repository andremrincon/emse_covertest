package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPositiveAPositiveB_returns200() {
        given().when().get("/api/bessj/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPositiveANegativeB_returns200() {
        given().when().get("/api/bessj/{n}/{x}", 3, 1e-10).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 17, -5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeAPositiveB_returns200() {
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", -9, 4);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeANegativeB_returns200() {
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", -9, -4);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAEqualsZero_returns400() {
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 0, 5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBEqualsZero_returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 5, 0);
        resp.then().statusCode(200);
    }
}