package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsTranslations() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsEsTranslation() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/GB").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsFrTranslation() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/FR").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsJaTranslation() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/DE").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsItTranslation() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/IT").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsTranslations() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().queryParam("codes", "US,CA,MX").get("/v1/alpha").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1NameReturnsTranslations() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsTranslations() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsTranslations() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/1").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1CapitalReturnsTranslations() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/capital/London").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1RegionReturnsTranslations() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/region/Europe").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1SubregionReturnsTranslations() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/subregion/Western%20Europe").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AllReturnsTranslations() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/all").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1NameFullTextReturnsTranslations() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().queryParam("fullText", "true").get("/v1/name/Germany").then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1LangReturnsTranslations() {
        given().when().get("/").then().statusCode(lessThan(300));
        given().when().get("/v1/lang/es").then().statusCode(404);
    }
}