package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("api.base", System.getenv("API_BASE"));
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSetDe_v1Alpha_US_invokesSetterAndReturnsDeTranslation_1() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        CountryTranslations ct = resp.jsonPath().getObject("translations", CountryTranslations.class);
        resp.then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testSetEs_v1Alpha_US_invokesSetterAndReturnsEsTranslation_2() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        CountryTranslations ct = resp.jsonPath().getObject("translations", CountryTranslations.class);
        resp.then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void testSetFr_v1Alpha_US_invokesSetterAndReturnsFrTranslation_3() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        CountryTranslations ct = resp.jsonPath().getObject("translations", CountryTranslations.class);
        resp.then().body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void testSetJa_v1Alpha_US_invokesSetterAndReturnsJaTranslation_4() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        CountryTranslations ct = resp.jsonPath().getObject("translations", CountryTranslations.class);
        resp.then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void testSetIt_v1Alpha_US_invokesSetterAndReturnsItTranslation_5() {
        given().when().get("/v1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        CountryTranslations ct = resp.jsonPath().getObject("translations", CountryTranslations.class);
        resp.then().body("translations.it", equalTo("Stati Uniti D'America"));
    }

    public static class CountryTranslations {
        public String de;
        public String es;
        public String fr;
        public String ja;
        public String it;

        public CountryTranslations() {
        }

        public String getDe() {
            return de;
        }

        public void setDe(String de) {
            this.de = de;
        }

        public String getEs() {
            return es;
        }

        public void setEs(String es) {
            this.es = es;
        }

        public String getFr() {
            return fr;
        }

        public void setFr(String fr) {
            this.fr = fr;
        }

        public String getJa() {
            return ja;
        }

        public void setJa(String ja) {
            this.ja = ja;
        }

        public String getIt() {
            return it;
        }

        public void setIt(String it) {
            this.it = it;
        }
    }
}