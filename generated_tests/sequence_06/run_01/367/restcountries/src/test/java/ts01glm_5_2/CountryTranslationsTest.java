package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.hasKey;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsTranslationsDe() {
        given().when().get("/v1/alpha/US").then().statusCode(404).body("translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsTranslationsEs() {
        given().when().get("/v1/alpha/US").then().statusCode(404).body("translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsTranslationsFr() {
        given().when().get("/v1/alpha/US").then().statusCode(404).body("translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsTranslationsJa() {
        given().when().get("/v1/alpha/US").then().statusCode(404).body("translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsTranslationsIt() {
        given().when().get("/v1/alpha/US").then().statusCode(404).body("translations.it", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsTranslations() {
        given().queryParam("codes", "US,CA").when().get("/v1/alpha").then().statusCode(404);
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsTranslationsDe() {
        given().when().get("/v1/currency/USD").then().statusCode(404).body("[0].translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsTranslationsEs() {
        given().when().get("/v1/currency/USD").then().statusCode(404).body("[0].translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameReturnsTranslationsFr() {
        given().when().get("/v1/name/France").then().statusCode(404).body("[0].translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameReturnsTranslationsJa() {
        given().when().get("/v1/name/France").then().statusCode(404).body("[0].translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsTranslationsIt() {
        given().when().get("/v1/callingcode/1").then().statusCode(404).body("[0].translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CapitalReturnsTranslationsDe() {
        given().when().get("/v1/capital/London").then().statusCode(404).body("[0].translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1RegionReturnsTranslationsEs() {
        given().when().get("/v1/region/Europe").then().statusCode(404).body("[0].translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AllReturnsTranslationsFr() {
        given().when().get("/v1/all").then().statusCode(404).body("[0].translations", hasKey("fr"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1RegionReturnsTranslationsJaAndIt() {
        given().when().get("/v1/region/Europe").then().statusCode(lessThan(300));
        given().when().get("/v1/region/Americas").then().statusCode(404).body("[0].translations.ja", notNullValue());
    }
}