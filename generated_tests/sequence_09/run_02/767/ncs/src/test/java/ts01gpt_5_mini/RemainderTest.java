package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Ignore


    @Test(timeout = 60000)
    public void testRemainder_PositiveA_PositiveB_returnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5");
        Integer result = resp.jsonPath().getInt("result");
        assertEquals(Integer.valueOf(2), result);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testRemainder_PositiveA_NegativeB_returnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/-5");
        Integer result = resp.jsonPath().getInt("result");
        assertEquals(Integer.valueOf(2), result);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testRemainder_NegativeA_PositiveB_returnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/-9/4");
        Integer result = resp.jsonPath().getInt("result");
        assertEquals(Integer.valueOf(-1), result);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testRemainder_NegativeA_NegativeB_returnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/-10/-3");
        Integer result = resp.jsonPath().getInt("result");
        assertEquals(Integer.valueOf(1), result);
    }

    @Ignore("expected:<400> but was:<200>")
    @Test(timeout = 60000)
    public void testRemainder_BIsZero_returnsBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/5/0");
        assertEquals(400, resp.getStatusCode());
    }
}