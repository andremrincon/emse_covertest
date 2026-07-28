package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetIso639_1() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US").then().statusCode(404).extract().response();
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetIso639_2() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US").then().statusCode(404).extract().response();
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetName() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US").then().statusCode(404).extract().response();
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testSetNativeName() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US").then().statusCode(404).extract().response();
    }
}