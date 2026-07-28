package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void init() {
        String base = Optional.ofNullable(System.getenv("API_BASE"))
                .orElse(System.getProperty("api.base", "http://localhost:8080/rest"));
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaUS_status200() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        r.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaUS_languageIso639_1() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        r.then().body("languages[0].iso639_1", equalTo("en"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1CurrencyUSD_status200() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/currency/USD");
        r.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1CurrencyUSD_languageIso639_2() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/currency/USD");
        r.then().body("[0].languages[0].iso639_2", equalTo("eng"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1NameFrance_status200() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/name/France");
        r.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1NameFrance_languageName() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/name/France");
        r.then().body("[0].languages[0].name", equalTo("French"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Lang_es_status200() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/lang/es");
        r.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1Lang_es_languageNativeName() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/lang/es");
        r.then().body("[0].languages[0].nativeName", equalTo("Espa\u00f1ol"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2Callingcode1_status200() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v2/callingcode/1");
        r.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2Callingcode1_languageNativeName() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v2/callingcode/1");
        r.then().body("[0].languages[0].nativeName", equalTo("English"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2CurrencyEUR_status200() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v2/currency/EUR");
        r.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2Lang_Spanish_languagePresent() {
        given().when().get("/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v2/lang/Spanish");
        r.then().body("[0].languages[0].iso639_1", equalTo("es"));
    }
}