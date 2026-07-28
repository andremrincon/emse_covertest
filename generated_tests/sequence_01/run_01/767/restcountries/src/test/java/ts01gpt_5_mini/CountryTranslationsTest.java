package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base","http://localhost:8080/rest");
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testSetDeViaAlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testSetEsViaCurrencyUSD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().body("[0].translations.es", equalTo("Samoa Americana"));
    }

    @Test(timeout = 60000)
    public void testSetFrViaNameFrance() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France").then().body("[0].translations.fr", equalTo("France"));
    }

    @Test(timeout = 60000)
    public void testSetJaViaCallingCode1() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/1").then().body("[0].translations.ja", equalTo("\u30a2\u30e1\u30ea\u30ab\u9818\u30b5\u30e2\u30a2"));
    }

    @Test(timeout = 60000)
    public void testSetItViaRegionEurope() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/region/Europe").then().body("[0].translations.it", equalTo("Isole Aland"));
    }
}