package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private static String baseUrl() {
        String s = System.getProperty("baseUrl");
        if (s != null && !s.trim().isEmpty()) return s;
        s = System.getenv("BASE_URL");
        if (s != null && !s.trim().isEmpty()) return s;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGammq_GserPath_Returns200_when_x_less_than_a_plus_one() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/gammq/5.5/2.3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_GcfPath_Returns200_when_x_ge_a_plus_one() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/gammq/5.5/1000.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidA_Negative_Returns400() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/gammq/-1.0/2.3");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidX_Negative_Returns400() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/gammq/5.5/-0.1");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_NonNumericA_Returns400() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/gammq/abc/2.3");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_xZero_UsesGser_Returns200() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/gammq/1.0/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_aZero_Returns400() {
        String base = baseUrl();
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/gammq/0.0/1.0");
        resp.then().statusCode(400);
    }
}