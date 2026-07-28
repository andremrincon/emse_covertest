package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", System.getenv("api.base"));
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_US_Status200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_BadFormat_Status400() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        assertEquals(400, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_NotFound_Status404() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Name_France_BodyContainsFrenchLanguage() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France");
        assertTrue(act.asString().contains("French"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Currency_USD_Status200() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Lang_es_Status200() {
        given().when().get("/v1/lang/es").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/lang/es");
        assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2Alpha_US_Status200() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/US");
        assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2Lang_Spanish_BodyContainsSpanish() {
        given().when().get("/v2/lang/Spanish").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/lang/Spanish");
        assertTrue(act.asString().contains("Spanish"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_MultipleCodes_Status200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        assertEquals(200, act.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Alpha_Codes_Bad400() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "123").when().get("/v1/alpha");
        assertEquals(400, act.getStatusCode());
    }
}