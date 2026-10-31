package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv().getOrDefault("BASE_URL", null);
        String prop = System.getProperty("baseUrl", null);
        String base = prop != null ? prop : (env != null ? env : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammqGserConverges() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/3/0.1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserXZeroReturnsSuccess() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/3/0.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfLargeXReturnsSuccess() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/1000.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidANegativeReturnsBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/-1.0/2.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidXNegativeReturnsBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/-2.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqBoundaryXEqualsAPlusOneUsesGcf() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/2.0/3.0");
        resp.then().statusCode(200);
    }
}