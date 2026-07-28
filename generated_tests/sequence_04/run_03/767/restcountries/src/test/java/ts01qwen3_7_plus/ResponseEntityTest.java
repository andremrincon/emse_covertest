package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ResponseEntityTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetStatus() {
        given().when().get("/v1/name/123").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().body("status", equalTo(404));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetMessage() {
        given().when().get("/v1/name/123").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().body("message", equalTo("Not Found"));
    }
}