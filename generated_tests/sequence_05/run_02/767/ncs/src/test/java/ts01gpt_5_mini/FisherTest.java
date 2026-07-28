package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    private static final String BASE;
    static {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_OddN_Returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 5, 3, 0.75);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_EvenN_Returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 5, 4, 0.75);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_EvenM_OddN_Returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 6, 3, 0.1);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_EvenM_EvenN_Returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 6, 4, 0.5);
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_InvalidM_Returns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get(BASE + "/api/fisher/abc/5/0.75");
        res.then().statusCode(400);
    }
}