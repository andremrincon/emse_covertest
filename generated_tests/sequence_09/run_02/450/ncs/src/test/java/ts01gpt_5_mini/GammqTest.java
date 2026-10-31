package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_WithZeroX_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/0.0");
        act.then().statusCode(equalTo(200));
    }

    @Test(timeout = 60000)
    public void testGammq_Gcf_WithLargeX_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/1000.0");
        act.then().statusCode(equalTo(200));
    }

    @Test(timeout = 60000)
    public void testGammq_NegativeX_returns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/-1.0");
        act.then().statusCode(equalTo(400));
    }

    @Test(timeout = 60000)
    public void testGammq_NonPositiveA_returns400() {
        given().when().get("/api/remainder/-9/4").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/-1.0/2.3");
        act.then().statusCode(equalTo(400));
    }

    @Test(timeout = 60000)
    public void testGammq_Boundary_X_equals_a_plus_one_triggersGcf_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/1.0/2.0");
        act.then().statusCode(equalTo(200));
    }

    @Test(timeout = 60000)
    public void testGammq_SmallA_ZeroX_triggersGser_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/0.001/0.0");
        act.then().statusCode(equalTo(200));
    }
}