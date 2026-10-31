package ts01gpt_5_mini_2;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private static final String BASE = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080"));

    @Test(timeout = 60000)
    public void testGammq_usesGser_whenXLessThanAPlusOne_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/5.5/2.3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_usesGcf_whenXGreaterThanAPlusOne_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/0.001/1000.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_withZeroX_triggersGamserZeroAndReturns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/5.5/0.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_withNegativeA_returns400() {
        given().when().get(BASE + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/-1.0/2.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_withNegativeX_returns400() {
        given().when().get(BASE + "/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/5.5/-1.0");
        act.then().statusCode(400);
    }
}