package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_gserPath_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/gammq/{a}/{x}", "5.5", "2.3");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_gcfPath_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/gammq/{a}/{x}", "0.001", "1000.0");
        res.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_invalidA_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/gammq/{a}/{x}", "-1.0", "2.3");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_negativeX_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/gammq/{a}/{x}", "5.5", "-1.0");
        res.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_zeroX_specialCase_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/gammq/{a}/{x}", "5.5", "0.0");
        res.then().statusCode(200);
    }
}