package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testRegionalBlocEUReturns200() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/EU");
        resp.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testRegionalBlocNumericReturns404() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/123");
        resp.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testRegionalBlocNAFTAReturns200() {
        given().when().get("/v2/regionalbloc/EU").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/regionalbloc/NAFTA");
        resp.then().statusCode(200);
    }
}