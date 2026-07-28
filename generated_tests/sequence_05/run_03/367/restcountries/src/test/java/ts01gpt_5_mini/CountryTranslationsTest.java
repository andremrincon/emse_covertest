package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CountryTranslationsTest {

    @BeforeClass
    public static void init() {
        String envBase = System.getProperty("API_BASE");
        if (envBase == null || envBase.isEmpty()) {
            String g = System.getenv("API_BASE");
            envBase = (g == null || g.isEmpty()) ? "http://localhost:8080/rest" : g;
        }
        RestAssured.baseURI = envBase;
    }

    @Test(timeout = 60000)
    public void testSetDeViaV1AlphaUS() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testSetEsViaV1AlphaUS() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void testSetFrViaV1AlphaUS() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void testSetJaViaV1AlphaUS() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void testSetItViaV1AlphaUS() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}