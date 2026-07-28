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
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSetDe_viaV1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().extract().response();
        CountryTranslations translations = act.jsonPath().getObject("translations", CountryTranslations.class);
        assertEquals("Vereinigte Staaten von Amerika", translations.getDe());
    }

    @Test(timeout = 60000)
    public void testSetEs_viaV1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().extract().response();
        CountryTranslations translations = act.jsonPath().getObject("translations", CountryTranslations.class);
        assertEquals("Estados Unidos", translations.getEs());
    }

    @Test(timeout = 60000)
    public void testSetFr_viaV1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().extract().response();
        CountryTranslations translations = act.jsonPath().getObject("translations", CountryTranslations.class);
        assertEquals("États-Unis", translations.getFr());
    }

    @Test(timeout = 60000)
    public void testSetJa_viaV1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().extract().response();
        CountryTranslations translations = act.jsonPath().getObject("translations", CountryTranslations.class);
        assertEquals("アメリカ合衆国", translations.getJa());
    }

    @Test(timeout = 60000)
    public void testSetIt_viaV1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US").then().extract().response();
        CountryTranslations translations = act.jsonPath().getObject("translations", CountryTranslations.class);
        assertEquals("Stati Uniti D'America", translations.getIt());
    }

    @Test(timeout = 60000)
    public void testAlphaEndpoint_returns400_forInvalidCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123").then().extract().response();
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAlphaEndpoint_returns404_forUnknownCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ").then().extract().response();
        assertEquals(404, act.getStatusCode());
    }

    public static class CountryTranslations {
        private String de;
        private String es;
        private String fr;
        private String ja;
        private String it;

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