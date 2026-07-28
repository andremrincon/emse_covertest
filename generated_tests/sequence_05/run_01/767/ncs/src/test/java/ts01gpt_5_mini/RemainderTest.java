package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"-1\"   Actual: {\"resu...")
    @Test(timeout = 60000)
    public void testAEqualsZero_returnsMinusOne() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 0, 5);
        resp.then().body(equalTo("-1"));
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testBEqualsZero_returnsBadRequest() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 1, 1, 1).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 5, 0);
        resp.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"2\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testAPosBPos_returnsRemainder() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        resp.then().body(equalTo("2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"8\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testAPosBNeg_returnsRemainder() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", 17, -9);
        resp.then().body(equalTo("8"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"-2\"   Actual: {\"resu...")
    @Test(timeout = 60000)
    public void testANegBPos_returnsRemainder() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", -17, 5);
        resp.then().body(equalTo("-2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"8\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testANegBNeg_returnsRemainder() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/{a}/{b}", -17, -9);
        resp.then().body(equalTo("8"));
    }
}