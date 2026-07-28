package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    private static final String BASE;
    static {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE");
        if (prop != null && !prop.isEmpty()) {
            BASE = prop;
        } else if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testAlphaUSTranslations_de_setsGermanTranslation() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha/US");
        act.then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testAlphaUSTranslations_es_setsSpanishTranslation() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha/US");
        act.then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void testAlphaUSTranslations_fr_setsFrenchTranslation() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha/US");
        act.then().body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void testAlphaUSTranslations_ja_setsJapaneseTranslation() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha/US");
        act.then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void testAlphaUSTranslations_it_setsItalianTranslation() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha/US");
        act.then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}