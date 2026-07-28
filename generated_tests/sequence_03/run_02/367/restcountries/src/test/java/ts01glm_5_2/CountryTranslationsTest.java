package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsTranslations() {
        given().when().get("/rest/v1/alpha/US").then().statusCode(404).body("translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeEsTranslation() {
        given().when().get("/rest/v1/alpha/FR").then().statusCode(404).body("translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeFrTranslation() {
        given().when().get("/rest/v1/alpha/DE").then().statusCode(404).body("translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeJaTranslation() {
        given().when().get("/rest/v1/alpha/GB").then().statusCode(404).body("translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeItTranslation() {
        given().when().get("/rest/v1/alpha/IT").then().statusCode(404).body("translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByNameReturnsTranslations() {
        given().when().get("/rest/v1/name/France").then().statusCode(404).body("translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCurrencyReturnsTranslations() {
        given().when().get("/rest/v1/currency/USD").then().statusCode(404).body("translations[0].es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCallingCodeReturnsTranslations() {
        given().when().get("/rest/v1/callingcode/44").then().statusCode(404).body("translations[0].fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCapitalReturnsTranslations() {
        given().when().get("/rest/v1/capital/London").then().statusCode(404).body("translations[0].ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByRegionReturnsTranslations() {
        given().when().get("/rest/v1/region/Europe").then().statusCode(404).body("translations[0].it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAllCountriesReturnsTranslations() {
        given().when().get("/rest/v1/all").then().statusCode(404).body("translations[0].de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAlphaCodesReturnsTranslations() {
        given().queryParam("codes", "US,CA").when().get("/rest/v1/alpha").then().statusCode(404).body("translations[0].de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByNameFullTextReturnsTranslations() {
        given().queryParam("fullText", "true").when().get("/rest/v1/name/Germany").then().statusCode(404).body("translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryBySubregionReturnsTranslations() {
        given().when().get("/rest/v1/subregion/Western%20Europe").then().statusCode(404).body("translations[0].es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByLangReturnsTranslations() {
        given().when().get("/rest/v1/lang/es").then().statusCode(404).body("translations[0].fr", notNullValue());
    }
}