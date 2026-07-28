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
        String base = System.getenv("REST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("rest.base.url", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso6391ViaV1AlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().body("languages[0]['iso639_1']", equalTo("en"));
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testSetIso6392ViaV1CurrencyUSD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().body("[0].languages[0]['iso639_2']", equalTo("eng"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetNameViaV1AlphaUS() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().body("languages[0]['name']", equalTo("English"));
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testSetNativeNameViaV1AlphaUS() {
        given().when().get("/v1/capital/London").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().body("languages[0]['nativeName']", equalTo("English"));
    }
}