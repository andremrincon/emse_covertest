package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE_URL", System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080/rest"));
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void test_setDe_v1Alpha_US_translations_de_present() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200).body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void test_setIt_v1Alpha_US_translations_it_present() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200).body("translations.it", equalTo("Stati Uniti D'America"));
    }

    @Test(timeout = 60000)
    public void test_setEs_v1Name_France_translations_es_present_in_first_item() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France");
        act.then().statusCode(200).body("[0].translations.es", equalTo("Francia"));
    }

    @Test(timeout = 60000)
    public void test_setFr_v1Region_Europe_translations_fr_present_in_first_item() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/Europe");
        act.then().statusCode(200).body("[0].translations.fr", equalTo("Åland"));
    }

    @Test(timeout = 60000)
    public void test_setJa_v1Currency_USD_translations_ja_present_in_first_item() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().statusCode(200).body("[0].translations.ja", equalTo("アメリカ領サモア"));
    }
}