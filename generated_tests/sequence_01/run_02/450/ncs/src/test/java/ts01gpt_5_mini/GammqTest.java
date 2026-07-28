package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_xZero_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/gammq/5.5/0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_smallX_gser_converges_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/gammq/5.5/0.001");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_largeX_gcf_path_returns200() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/gammq/5.5/1000.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_negativeX_returns400() {
        given().when().get(base + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/gammq/5.5/-1");
        resp.then().statusCode(400);
    }
}