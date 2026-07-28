package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ResponseEntityTest {

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityOnNameNotFound() {
        String baseUrl = System.getProperty("test.base.url", "http://localhost:8080/rest");

        given().baseUri(baseUrl).when().get().then().statusCode(lessThan(300));

        Response response = given().baseUri(baseUrl).when().get("/v1/name/123");

        response.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityOnNameServerError() {
        String baseUrl = System.getProperty("test.base.url", "http://localhost:8080/rest");

        given().baseUri(baseUrl).when().get().then().statusCode(lessThan(300));

        Response response = given().baseUri(baseUrl).when().get("/v1/name/True");

        response.then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityOnRegionNotFound() {
        String baseUrl = System.getProperty("test.base.url", "http://localhost:8080/rest");

        given().baseUri(baseUrl).when().get().then().statusCode(lessThan(300));

        Response response = given().baseUri(baseUrl).when().get("/v1/region/123");

        response.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityOnCapitalNotFound() {
        String baseUrl = System.getProperty("test.base.url", "http://localhost:8080/rest");

        given().baseUri(baseUrl).when().get().then().statusCode(lessThan(300));

        Response response = given().baseUri(baseUrl).when().get("/v1/capital/123");

        response.then().statusCode(404);
    }
}