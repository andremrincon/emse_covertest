package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("api.base", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_usesGser_branch_when_x_less_than_a_plus_one_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_usesGcf_branch_when_x_greater_or_equal_a_plus_one_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, 1000.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_with_x_equal_zero_triggers_gser_x_zero_path_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, 0.0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_with_negative_x_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, -1.0);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_with_nonpositive_a_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", -1.0, 2.0);
        resp.then().statusCode(400);
    }
}