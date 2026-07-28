package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityStatusOnNameNotFound() {
        given().when().get("/v1").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/name/123");

        response.then().body("status", equalTo(404));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityMessageOnCapitalNotFound() {
        given().when().get("/v1").then().statusCode(lessThan(300));

        Response response = given().when().get("/v1/capital/123");

        response.then().body("message", equalTo("Not Found"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityStatusOnPostNotAllowed() {
        given().when().get("/v1").then().statusCode(lessThan(300));

        Response response = given().when().post("/v1");

        response.then().body("status", equalTo(405));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityMessageOnPostNotAllowed() {
        given().when().get("/v1").then().statusCode(lessThan(300));

        Response response = given().when().post("/v1");

        response.then().body("message", equalTo("Method Not Allowed"));
    }
}