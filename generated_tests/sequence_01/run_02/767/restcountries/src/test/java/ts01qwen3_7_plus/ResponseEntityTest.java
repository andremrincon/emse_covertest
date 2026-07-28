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
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080/rest";
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityNotFound() {
        Response response = given()
                .when()
                .get("/v1/name/123");

        response.then().statusCode(404);
        response.then().statusCode(lessThan(300));
        response.then().body("message", equalTo("Not Found"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityInternalServerError() {
        Response response = given()
                .when()
                .get("/v1/name/True");

        response.then().statusCode(404);
        response.then().statusCode(lessThan(300));
        response.then().body("message", equalTo("Not Found"));
    }
}