package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String base = Optional.ofNullable(System.getProperty("ncs.baseUrl"))
                .orElse(Optional.ofNullable(System.getenv("NCS_BASE_URL")).orElse("http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjReturns200ForValidParameters() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 3, 2.5);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessjReturns400ForInvalidN() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/{n}/{x}", 2, 2.5);
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpintReturns200ForValidParameters() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpintReturns400ForInvalidN() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/{n}/{x}", -1, 0.1);
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherReturns200ForValidParameters() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenMExceedsLimit() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/{m}/{n}/{x}", 1001, 5, 0.5);
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqReturns200ForValidParameters() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqReturns400ForInvalidA() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/{a}/{x}", -1.0, 2.0);
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderReturns200ForValidParameters() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderReturns400WhenValueExceedsLimit() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 10001, 1);
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testTriangleReturns200ForRightTriangle() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5);
        assertEquals(200, resp.getStatusCode());
    }
}