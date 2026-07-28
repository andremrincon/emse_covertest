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
        String base = System.getProperty("base.url", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_SmallX_Status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/0.001");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_ZeroX_Status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gcf_LargeX_Status200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/1000.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_NegativeX_Status400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/-1.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidA_Zero_Status400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/0.0/1.0");
        resp.then().statusCode(400);
    }
}