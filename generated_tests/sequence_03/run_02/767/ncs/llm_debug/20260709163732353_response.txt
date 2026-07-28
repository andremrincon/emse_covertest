package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {
    private static final String BASE;
    static {
        String b = System.getProperty("baseUrl");
        if (b == null) b = System.getenv("BASE_URL");
        if (b == null) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_OddN_Returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 3, 3, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_EvenN_Returns200() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 3, 4, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_EvenM_OddN_Returns200() {
        given().when().get(BASE + "/api/expint/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 4, 3, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_EvenM_EvenN_Returns200() {
        given().when().get(BASE + "/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 4, 2, 0.75);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_WithXZero_Returns200() {
        given().when().get(BASE + "/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/{m}/{n}/{x}", 10, 5, 0.0);
        act.then().statusCode(200);
    }
}