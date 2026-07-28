package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_OddOdd_returns200() {
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 6, 0.75).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 6, 5, 0.75).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 11, 5, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_OddEven_returns200() {
        given().when().get("/api/fisher/{m}/{n}/{x}", 11, 5, 0.75).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 11, 6, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_EvenOdd_returns200() {
        given().when().get("/api/fisher/{m}/{n}/{x}", 11, 6, 0.75).then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 11, 5, 0.75).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75);
        act.then().statusCode(200);
    }
}