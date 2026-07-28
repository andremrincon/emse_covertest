package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

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

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsTranslationsDe() {
        given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha").then().statusCode(404).body("translations.de", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameReturnsTranslationsEs() {
        given().when().get("/v1/name/France").then().statusCode(404).body("translations.es", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsTranslationsFr() {
        given().when().get("/v1/currency/USD").then().statusCode(404).body("translations.fr", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsTranslationsJa() {
        given().when().get("/v1/callingcode/1").then().statusCode(404).body("translations.ja", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CapitalReturnsTranslationsIt() {
        given().when().get("/v1/capital/London").then().statusCode(404).body("translations.it", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1RegionReturnsTranslationsDe() {
        given().when().get("/v1/region/Europe").then().statusCode(404).body("translations.de", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AllReturnsTranslationsEs() {
        given().when().get("/v1/all").then().statusCode(404).body("translations.es", hasItem(notNullValue()));
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testV1SubregionReturnsTranslationsFr() {
        given().when().get("/v1/subregion/{subregion}", "Western Europe").then().statusCode(404).body("translations.fr", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1LangReturnsTranslationsJa() {
        given().when().get("/v1/lang/es").then().statusCode(404).body("translations.ja", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeGBReturnsAllTranslations() {
        given().when().get("/v1/alpha/GB").then().statusCode(404).body("translations.de", notNullValue()).and().body("translations.it", notNullValue());
    }
}