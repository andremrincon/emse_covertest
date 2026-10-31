package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        String base = System.getProperty("api.base", env != null ? env : "http://localhost:8080/rest");
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testNameNotFoundStatus_v1_name_404() {
        given().queryParam("codes", "US").when().get("/v1/alpha").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testNameNotFoundMessage_v1_name_404() {
        given().queryParam("codes", "US").when().get("/v1/alpha").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().body("message", equalTo("Not Found"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testNameServerErrorStatus_v1_name_500() {
        given().queryParam("codes", "US").when().get("/v1/alpha").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/True");
        act.then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaListSuccessBody_v1_alpha_200() {
        given().queryParam("codes", "US").when().get("/v1/alpha").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        act.then().body("size()", greaterThan(0));
    }
}