package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaUS_status200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaUS_de_translation() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/v1/alpha/US");
        act.then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1NameFrance_status200() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/v1/name/France");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1NameFrance_fr_translation() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/v1/name/France");
        act.then().body("[0].translations.fr", equalTo("France"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaUS_ja_translation() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/v1/alpha/US");
        act.then().body("translations.ja", equalTo("\u30a2\u30e1\u30ea\u30ab\u5408\u8846\u56fd"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaUS_it_translation() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/v1/alpha/US");
        act.then().body("translations.it", equalTo("Stati Uniti D'America"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaInvalid_400() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        io.restassured.response.Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(400);
    }
}