package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjValid() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/2.5");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNLow() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/2/2.5");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNHigh() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/1001/2.5");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherValid() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/0.75");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherInvalidHighM() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/1001/5/0.75");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherRuntimeExceptionForX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/0.5");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqValid() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/2.3");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/-1.0/2.3");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderValid() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderOutOfBounds() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/10001/1");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpintValid() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/3/2.5");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testExpintInvalid() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/expint/-1/2.5");
        assertEquals(400, resp.getStatusCode());
    }
}