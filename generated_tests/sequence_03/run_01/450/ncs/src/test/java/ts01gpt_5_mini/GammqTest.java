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
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_Normal() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/0.001");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gcf_Normal() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/1000.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_XZero_TriggersGser() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_NegativeX_BadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/-1.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_NonPositiveA_BadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/0/1.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_NonNumericA_BadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", "abc", "2.3");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_EqualityBoundary_UsesGcf() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/2.5/3.5");
        resp.then().statusCode(200);
    }
}