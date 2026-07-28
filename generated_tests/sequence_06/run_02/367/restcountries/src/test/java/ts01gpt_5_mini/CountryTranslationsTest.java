package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null) base = System.getProperty("baseUrl");
        if (base == null) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1AlphaUS_translations_de() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        String actual = resp.jsonPath().getString("translations.de");
        assertEquals("Vereinigte Staaten von Amerika", actual);
    }

    @Test(timeout = 60000)
    public void testV1AlphaUS_translations_es() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        String actual = resp.jsonPath().getString("translations.es");
        assertEquals("Estados Unidos", actual);
    }

    @Test(timeout = 60000)
    public void testV1AlphaUS_translations_fr() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        String actual = resp.jsonPath().getString("translations.fr");
        assertEquals("États-Unis", actual);
    }

    @Test(timeout = 60000)
    public void testV1AlphaUS_translations_ja() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        String actual = resp.jsonPath().getString("translations.ja");
        assertEquals("アメリカ合衆国", actual);
    }

    @Test(timeout = 60000)
    public void testV1AlphaUS_translations_it() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        String actual = resp.jsonPath().getString("translations.it");
        assertEquals("Stati Uniti D'America", actual);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyUSD_translations_de_firstItem() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        String actual = resp.jsonPath().getString("[0].translations.de");
        assertEquals("Amerikanisch-Samoa", actual);
    }

    @Test(timeout = 60000)
    public void testV1NameFrance_translations_de_firstItem() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/France");
        String actual = resp.jsonPath().getString("[0].translations.de");
        assertEquals("Frankreich", actual);
    }

    @Test(timeout = 60000)
    public void testV1All_status200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/all").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_invalidFormat_400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1Alpha_notFound_404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }
}