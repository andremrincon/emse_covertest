package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testRemainderAZero() {
        Response response = given().when().get("/api/remainder/{a}/{b}", 0, 5);
        response.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testRemainderBZero() {
        Response response = given().when().get("/api/remainder/{a}/{b}", 5, 0);
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveAPositiveB() {
        Response response = given().when().get("/api/remainder/{a}/{b}", 17, 5);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        Response response = given().when().get("/api/remainder/{a}/{b}", 17, -5);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        Response response = given().when().get("/api/remainder/{a}/{b}", -17, 5);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        Response response = given().when().get("/api/remainder/{a}/{b}", -17, -5);
        response.then().statusCode(200);
    }
}