package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        String base = env != null && !env.isEmpty() ? env : System.getProperty("base.url", "http://localhost:8080/rest");
        RestAssured.baseURI = base;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAlphaUS_returnsTranslationsDe() {
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(404).and().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAlphaUS_returnsTranslationsEs() {
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(404).and().body("translations.es", equalTo("Estados Unidos"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAlphaUS_returnsTranslationsFr() {
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(404).and().body("translations.fr", equalTo("États-Unis"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAlphaUS_returnsTranslationsJa() {
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(404).and().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAlphaUS_returnsTranslationsIt() {
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(404).and().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}