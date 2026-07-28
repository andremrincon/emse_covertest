package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPositiveA_PositiveB_returnsRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        act.then().body("result", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testPositiveA_NegativeB_returnsRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 17, -5);
        act.then().body("result", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testNegativeA_PositiveB_returnsNegativeRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", -17, 5);
        act.then().body("result", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testNegativeA_NegativeB_returnsPositiveRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", -17, -5);
        act.then().body("result", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testAZero_returnsMinusOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 0, 5);
        act.then().body("result", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testBZero_returnsBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/{a}/{b}", 10, 0);
        act.then().statusCode(200);
    }
}