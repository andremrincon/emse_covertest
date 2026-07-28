package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;

public class FisherTest {

    private static final String BASE = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));

    @Test(timeout = 60000)
    public void testFisher_a1b1_loop() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/3/5/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1bNot1_loop() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/3/4/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_b1_aNot1() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/2/3/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_neither1_with_i_loop() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/4/2/0.75");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_edge_x_zero() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/fisher/10/5/0.0");
        act.then().statusCode(200);
    }
}