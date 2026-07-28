package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private static final String BASE;
    static {
        String b = System.getProperty("baseUrl");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testGammq_GserZeroX_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/5.5/0.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_GserSmallX_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/5.5/0.001");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_GcfLargeX_returns200() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/5.5/1000.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidA_returns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/-1.0/2.3");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_NegativeX_returns400() {
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/gammq/5.5/-0.1");
        act.then().statusCode(400);
    }
}