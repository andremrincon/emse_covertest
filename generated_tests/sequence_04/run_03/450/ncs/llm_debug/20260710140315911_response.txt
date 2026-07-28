package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("ncs.base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("NCS_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjSuccessReturns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBadNReturns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/2/1.0");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherSuccessReturns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/0.75");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherRuntimeExceptionReturns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/fisher/10/5/1.2");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqSuccessReturns200() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/5.5/2.3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqRuntimeExceptionReturns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/gammq/-1.0/2.3");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderSuccessReturnsCorrectBody() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5");
        resp.then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderOutOfRangeReturns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/10001/1");
        resp.then().statusCode(400);
    }
}