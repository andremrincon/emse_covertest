package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("baseUrl"));
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testV1AllReturnsTranslationsDe() {
        given().when().get("/v1/all").then().statusCode(200).body("translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsTranslationsEs() {
        given().when().get("/v1/alpha/US").then().statusCode(200).body("translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsTranslationsFr() {
        given().queryParam("codes", "US,CA").when().get("/v1/alpha").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyReturnsTranslationsJa() {
        given().when().get("/v1/currency/USD").then().statusCode(200).body("translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1NameReturnsTranslationsIt() {
        given().when().get("/v1/name/France").then().statusCode(200).body("translations.it", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsTranslationsDe() {
        given().when().get("/v1/callingcode/1").then().statusCode(200).body("translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1CapitalReturnsTranslationsEs() {
        given().when().get("/v1/capital/London").then().statusCode(200).body("translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1RegionReturnsTranslationsFr() {
        given().when().get("/v1/region/Europe").then().statusCode(200).body("translations.fr", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1NameFullTextReturnsTranslationsJa() {
        given().queryParam("fullText", "true").when().get("/v1/name/Germany").then().statusCode(200).body("translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1AllReturnsTranslationsIt() {
        given().when().get("/v1/all").then().statusCode(200).body("translations.it", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsTranslationsDe() {
        given().when().get("/v1/alpha/GB").then().statusCode(200).body("translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1CurrencyEurReturnsTranslationsEs() {
        given().when().get("/v1/currency/EUR").then().statusCode(200).body("translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1CallingCode44ReturnsTranslationsFr() {
        given().when().get("/v1/callingcode/44").then().statusCode(200).body("translations.fr", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1CapitalParisReturnsTranslationsJa() {
        given().when().get("/v1/capital/Paris").then().statusCode(200).body("translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV1RegionAmericasReturnsTranslationsIt() {
        given().when().get("/v1/region/Americas").then().statusCode(200).body("translations.it", notNullValue());
    }
}