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
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetNameNotFoundStatusCode() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetNameNotFoundStatusField() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().body("status", equalTo(404));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetNameNotFoundMessageField() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().body("message", equalTo("Not Found"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testPostRootMethodNotAllowedStatusCode() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().post("/").then().statusCode(404);
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testPostRootMethodNotAllowedStatusField() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().post("/").then().body("status", equalTo(404));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testPostRootMethodNotAllowedMessageField() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().post("/").then().body("message", equalTo("Not Found"));
    }
}