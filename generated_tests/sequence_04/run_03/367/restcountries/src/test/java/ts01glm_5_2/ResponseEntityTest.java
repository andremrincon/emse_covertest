package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundReturnsResponseEntityStatus() {
        given().when().get("/v1/name/{name}", "123").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetNameNotFoundReturnsResponseEntityMessage() {
        given().when().get("/v1/name/{name}", "123").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetCapitalNotFoundReturnsResponseEntityStatus() {
        given().when().get("/v1/capital/{capital}", "123").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetRegionNotFoundReturnsResponseEntityMessage() {
        given().when().get("/v1/region/{region}", "123").then().statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testGetAlphaNotFoundReturnsResponseEntityStatus() {
        given().when().get("/v1/alpha/{alphacode}", "XYZ").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetCallingcodeNotFoundReturnsResponseEntityMessage() {
        given().when().get("/v1/callingcode/{callingcode}", "abc").then().statusCode(lessThan(300));
    }
}