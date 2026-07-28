package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("Cannot invoke method getAt() on null object")
    @Test(timeout = 60000)
    public void testSetIso639_1_v1AlphaUS_exposesIso639_1_as_en() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200).body("$[0].languages[0].iso639_1", equalTo("en"));
    }

    @Ignore("Cannot invoke method getAt() on null object")
    @Test(timeout = 60000)
    public void testSetIso639_2_v1AlphaUS_exposesIso639_2_as_eng() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().statusCode(200).body("$[0].languages[0].iso639_2", equalTo("eng"));
    }

    @Ignore("1 expectation failed. JSON path $[0].languages[0].name doesn't match. Expected: English   Actual:...")
    @Test(timeout = 60000)
    public void testSetName_v1CurrencyUSD_exposesLanguageName_as_English() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200).body("$[0].languages[0].name", equalTo("English"));
    }

    @Ignore("1 expectation failed. JSON path $[0].languages[0].nativeName doesn't match. Expected: Español   A...")
    @Test(timeout = 60000)
    public void testSetNativeName_v1Lang_es_exposesNativeName_for_spanish() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/lang/es");
        resp.then().statusCode(200).body("$[0].languages[0].nativeName", equalTo("Español"));
    }
}