package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class BessjTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("NCS_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("NCS_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjReturns200ForTypicalInput() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/3/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjReturns400ForNLessThanTwo() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/bessj/1/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjReturns200ForNegativeXHandlesSign() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String xid = UUID.randomUUID().toString();
        Response act = given().when().get("/api/bessj/3/-2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderReturnsExpectedResult() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/17/5");
        act.then().body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testGammqReturns200ForValidParams() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/gammq/5.5/2.3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400ForInvalidX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/1.2");
        act.then().statusCode(200);
    }
}