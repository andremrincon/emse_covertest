package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("ncs.baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("NCS_BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testBessjValidReturns200() {
        given().when().get(RestAssured.baseURI + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        Response resp = given().when().get(RestAssured.baseURI + "/api/bessj/3/2.5");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBessjNTooSmallReturns400() {
        given().when().get(RestAssured.baseURI + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/bessj/2/1.0");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherValidReturns200() {
        given().when().get(RestAssured.baseURI + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/fisher/10/5/0.75");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherMTooLargeReturns400() {
        given().when().get(RestAssured.baseURI + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/fisher/1001/5/0.5");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherInvalidXRuntimeLeads400() {
        given().when().get(RestAssured.baseURI + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/fisher/10/5/1.2");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqValidReturns200() {
        given().when().get(RestAssured.baseURI + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/gammq/5.5/2.3");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAThrowsReturns400() {
        given().when().get(RestAssured.baseURI + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/gammq/-1.0/2.0");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderValidReturns200() {
        given().when().get(RestAssured.baseURI + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/remainder/17/5");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderOutOfBoundsReturns400() {
        given().when().get(RestAssured.baseURI + "/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get(RestAssured.baseURI + "/api/remainder/10001/2");
        assertEquals(400, resp.getStatusCode());
    }
}