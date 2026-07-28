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
        String base = System.getProperty("API_BASE_URL");
        if (base == null) base = System.getenv("API_BASE_URL");
        if (base == null) base = System.getProperty("base.url");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_gser_convergent_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/0.001");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gcf_path_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/1000.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gser_x_zero_returns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_negative_x_returns400() {
        given().when().get("/api/remainder/4/3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/-1.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_nonpositive_a_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/0.0/2.3");
        resp.then().statusCode(400);
    }
}