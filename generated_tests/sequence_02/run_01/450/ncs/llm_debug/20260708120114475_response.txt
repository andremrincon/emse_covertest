package ts01gpt_5_mini;
import org.junit.Test;
import org.junit.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
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
        Response resp = given().when().get("/api/gammq/5.5/2.3");
        resp.then().statusCode(200);
    }
    @Test(timeout = 60000)
    public void testGammq_gcfPath_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/0.001/1000.0");
        resp.then().statusCode(200);
    }
    @Test(timeout = 60000)
    public void testGammq_xZero_triggersGserAndReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/0.0");
        resp.then().statusCode(200);
    }
    @Test(timeout = 60000)
    public void testGammq_negativeX_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/-1.0");
        resp.then().statusCode(400);
    }
    @Test(timeout = 60000)
    public void testGammq_nonPositiveA_returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/0.0/1.0");
        resp.then().statusCode(400);
    }
}