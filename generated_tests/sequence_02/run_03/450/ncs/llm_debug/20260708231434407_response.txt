package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_n0_x25_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 0, 2.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_n1_x0_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 1, 0);
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_n2_x0_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 2, 0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_n3_x25_continuedFraction_returns200() {
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_n3_x01_series_returns200() {
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 0.1);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_negative_x_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, -1.0);
        resp.then().statusCode(400);
    }
}