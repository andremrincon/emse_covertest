package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSetDe_v1Alpha_US_translated_de() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200).body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testSetEs_v1Currency_USD_translated_es() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().statusCode(200).body("[0].translations.es", equalTo("Samoa Americana"));
    }

    @Test(timeout = 60000)
    public void testSetFr_v1Name_France_translated_fr() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France");
        act.then().statusCode(200).body("[0].translations.fr", equalTo("France"));
    }

    @Test(timeout = 60000)
    public void testSetJa_v1Callingcode_1_translated_ja() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/1");
        act.then().statusCode(200).body("[0].translations.ja", equalTo("アメリカ領サモア"));
    }

    @Test(timeout = 60000)
    public void testSetIt_v1Region_Europe_translated_it() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/Europe");
        act.then().statusCode(200).body("[0].translations.it", equalTo("Isole Aland"));
    }
}