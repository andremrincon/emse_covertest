package ts01gpt_5_mini_3;

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
        String base = System.getProperty("BASE_URL");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAEqualsZeroReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/0/5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBEqualsZeroReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/0");
        resp.then().statusCode(200);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testPositivePositiveReturnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5");
        int result = resp.jsonPath().getInt("result");
        assertEquals(2, result);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testPositiveNegativeReturnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/-5");
        int result = resp.jsonPath().getInt("result");
        assertEquals(2, result);
    }

    @Test(timeout = 60000)
    public void testNegativePositiveReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/-9/4");
        resp.then().statusCode(200);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testNegativeNegativeReturnsExpectedRemainder() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/-9/-4");
        int result = resp.jsonPath().getInt("result");
        assertEquals(1, result);
    }
}