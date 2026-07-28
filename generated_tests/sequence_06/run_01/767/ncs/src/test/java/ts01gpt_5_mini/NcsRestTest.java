package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NcsRestTest {

    @Before
    public void setUp(){
        String base = System.getProperty("NCS_BASE_URL");
        if(base == null || base.isEmpty()){
            base = System.getenv("NCS_BASE_URL");
        }
        if(base == null || base.isEmpty()){
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjValid200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/2.5");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessjInvalidN400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/2/2.5");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherValid200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/0.75");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherInvalidLargeParams400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/1001/5/0.75");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherRuntimeException400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/1.2");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderValid200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidLimit400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/10001/1");
        assertEquals(400, resp.getStatusCode());
    }
}