package ts01gpt_5_mini_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    private static final String BASE;
    static {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
            if (base == null || base.isEmpty()) {
                base = "http://localhost:8080/rest";
            }
        }
        BASE = base;
    }

    @Test(timeout = 60000)
    public void testSetDe_v1AlphaByCode_shouldHaveGermanTranslation() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha/US").then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testSetEs_v1AlphaByCode_shouldHaveSpanishTranslation() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha/US").then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void testSetFr_v1AlphaByCode_shouldHaveFrenchTranslation() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha/US").then().body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void testSetJa_v1AlphaByCode_shouldHaveJapaneseTranslation() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha/US").then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void testSetIt_v1AlphaByCode_shouldHaveItalianTranslation() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha/US").then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}