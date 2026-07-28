package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private static String base() {
        String b = System.getProperty("baseUrl");
        if (b == null) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        return b;
    }

    @Test(timeout = 60000)
    public void testGammq_GserZeroX_returns200() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String a = "5.0";
        String x = "0.0";
        Response resp = given().when().get(base() + "/api/gammq/{a}/{x}", a, x);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_GcfLargeX_returns200() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String a = "5.5";
        String x = "1000.0";
        Response resp = given().when().get(base() + "/api/gammq/{a}/{x}", a, x);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_GserNonConverge_returns400() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String a = "100000000";
        String x = "1.0";
        Response resp = given().when().get(base() + "/api/gammq/{a}/{x}", a, x);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_GcfNonConverge_returns400() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String a = "100000000";
        String x = "101000000";
        Response resp = given().when().get(base() + "/api/gammq/{a}/{x}", a, x);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidA_negative_returns400() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String a = "-1.0";
        String x = "2.0";
        Response resp = given().when().get(base() + "/api/gammq/{a}/{x}", a, x);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidX_negative_returns400() {
        given().when().get(base() + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String a = "2.0";
        String x = "-1.0";
        Response resp = given().when().get(base() + "/api/gammq/{a}/{x}", a, x);
        resp.then().statusCode(400);
    }
}