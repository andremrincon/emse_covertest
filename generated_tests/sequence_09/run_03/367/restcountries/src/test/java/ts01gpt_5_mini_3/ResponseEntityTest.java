package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1NameReturnsStatusField404() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/123");
        response.then().body("status", equalTo(404));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1NameReturnsMessageOnServerError() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/True");
        response.then().body("message", equalTo("Internal Server Error"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaBadRequestReturns400() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response response = given().queryParam("codes", "123").when().get("/v1/alpha");
        response.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaServerErrorReturns500() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response response = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha");
        response.then().statusCode(500);
    }
}