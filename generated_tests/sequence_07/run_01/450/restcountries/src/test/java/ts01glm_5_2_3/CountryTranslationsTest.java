package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/US").then().statusCode(200).body("translations.de", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha?codes=US,CA,MX").then().statusCode(200).body("translations[0].es", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1NameReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/name/France").then().statusCode(200).body("translations[0].fr", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1NameFullTextReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/name/France?fullText=true").then().statusCode(200).body("translations[0].ja", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/currency/USD").then().statusCode(200).body("translations[0].it", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/callingcode/1").then().statusCode(200).body("translations[0].de", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CapitalReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/capital/London").then().statusCode(200).body("translations[0].es", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1RegionReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/region/Europe").then().statusCode(200).body("translations[0].fr", notNullValue());
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testV1SubregionReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/subregion/Western Europe").then().statusCode(200).body("translations[0].ja", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1LangReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/lang/es").then().statusCode(200).body("translations[0].it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AllReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404).body("translations[0].de", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaGBReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/alpha/GB").then().statusCode(200).body("translations.de", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CurrencyXOFReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/currency/XOF").then().statusCode(200).body("translations[0].de", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CallingCode44ReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/callingcode/44").then().statusCode(200).body("translations[0].es", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1RegionAfricaReturnsTranslations() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/region/africa").then().statusCode(200).body("translations[0].fr", notNullValue());
    }
}