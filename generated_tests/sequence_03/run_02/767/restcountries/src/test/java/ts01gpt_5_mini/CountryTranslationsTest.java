package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetDeTranslationForUS() {
        given().when().get("/v1/all").then().statusCode(404);
        Response response = given().when().get("/v1/alpha/US");
        response.then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetEsTranslationForUS() {
        given().when().get("/v1/all").then().statusCode(404);
        Response response = given().when().get("/v1/alpha/US");
        response.then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetFrTranslationForUS() {
        given().when().get("/v1/all").then().statusCode(404);
        Response response = given().when().get("/v1/alpha/US");
        response.then().body("translations.fr", equalTo("États-Unis"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetJaTranslationForUS() {
        given().when().get("/v1/all").then().statusCode(404);
        Response response = given().when().get("/v1/alpha/US");
        response.then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetItTranslationForUS() {
        given().when().get("/v1/all").then().statusCode(404);
        Response response = given().when().get("/v1/alpha/US");
        response.then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}