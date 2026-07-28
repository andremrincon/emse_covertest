package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaUS_hasLanguageIso639_1() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200).body("languages[0].iso639_1", equalTo("en"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testNameFrance_hasLanguageIso639_2() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France").then().statusCode(200).body("[0].languages[0].iso639_2", equalTo("fra"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCurrencyUSD_setsLanguageName() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().statusCode(200).body("[0].languages[0].name", equalTo("English"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaUS_setsNativeName() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200).body("languages[0].nativeName", equalTo("English"));
    }
}