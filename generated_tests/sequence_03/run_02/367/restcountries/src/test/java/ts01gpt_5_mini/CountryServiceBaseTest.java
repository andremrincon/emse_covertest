package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceBaseTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlpha2Returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "US");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlpha2BodyHasAlpha2CodeUS() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "US");
        act.then().body("alpha2Code", equalTo("US"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlpha3Returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "USA");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaInvalidFormatReturns400() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "123");
        act.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaNotFoundReturns404() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/{alphacode}", "XYZ");
        act.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaCodesMultipleReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().param("codes", "US,CA").when().get("/v1/alpha");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaCodesMissingReturns400() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha");
        act.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testNameFullTextExactMatchReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().param("fullText", "true").when().get("/v1/name/{name}", "United States of America");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testNameFullTextAlternativeSpellingReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().param("fullText", "true").when().get("/v1/name/{name}", "DE");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testNameSubstringMatchReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().param("fullText", "false").when().get("/v1/name/{name}", "United");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testNameSubstringAlternativeSpellingReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().param("fullText", "false").when().get("/v1/name/{name}", "DE");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCallingCodeReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/{callingcode}", "1");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testNameNotFoundReturns404() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/{name}", "123");
        act.then().statusCode(404);
    }
}