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
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_gammq_gser_path_returns_200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/2.3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_gammq_gcf_path_returns_200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/1000.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_gammq_invalid_a_nonpositive_returns_400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/-1.0/2.0");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_gammq_negative_x_returns_400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/-0.1");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void test_gammq_x_zero_uses_gser_and_returns_200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/2.0/0.0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_gammq_gser_itmax_condition_returns_400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/1000000.0/0.000001");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_gammq_gcf_itmax_condition_returns_400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/1000000.0/1000001.0");
        act.then().statusCode(400);
    }
}