package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammqGserSuccess() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/gammq/5.5/0.001");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqGcfSuccess() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/gammq/5.5/1000.0");
        assertEquals(200, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqInvalidANegativeReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/gammq/-1.0/2.3");
        assertEquals(400, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqInvalidXNegativeReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response res = given().when().get("/api/gammq/5.5/-0.1");
        assertEquals(400, res.getStatusCode());
    }
}