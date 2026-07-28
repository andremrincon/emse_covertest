package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("REST_BASE_URL", System.getProperty("rest.base", "http://localhost:8080/rest"));
    }

    @Test(timeout = 60000)
    public void testAlphaUSTranslationsDe() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testAlphaUSTranslationsEs() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void testAlphaUSTranslationsFr() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void testAlphaUSTranslationsJa() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void testAlphaUSTranslationsIt() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        resp.then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}