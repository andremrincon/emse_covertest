package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_gser_whenXZero_returns200() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "5.5", "0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gser_whenXNegative_returns400() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "5.5", "-0.1");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_gser_converges_returns200() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "5.5", "2.3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gcf_path_smallA_largeX_returns200() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "0.001", "1000.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gcf_path_largeX_withDecimalA_returns200() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "5.5", "1000.0");
        resp.then().statusCode(200);
    }
}