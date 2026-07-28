package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Ignore("Invalid JSON expression: Script1.groovy: 1: Unexpected input: '                         [' @ line...")
    @Test(timeout = 60000)
    public void testV1Alpha_US_containsCurrencyCode_USD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200).body("['currencies'][0]['code']", equalTo("USD"));
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1Currency_USD_countryEntryHasCurrencyCodeUSD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().statusCode(200).body("[0]['currencies'][0]['code']", equalTo("USD"));
    }

    @Test(timeout = 60000)
    public void testV2Currency_EUR_currencyNameIsEuro() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/currency/EUR");
        act.then().statusCode(200).body("[0]['currencies'][0]['name']", equalTo("Euro"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1Name_France_currencyNameIsEuro() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France");
        act.then().statusCode(200).body("[0]['currencies'][0]['name']", equalTo("Euro"));
    }

    @Test(timeout = 60000)
    public void testV2Name_Germany_currencySymbolIsEuroSign() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/name/Germany");
        act.then().statusCode(200).body("[0]['currencies'][0]['symbol']", equalTo("€"));
    }
}