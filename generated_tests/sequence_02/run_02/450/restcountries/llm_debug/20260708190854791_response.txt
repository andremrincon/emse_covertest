package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    private static String BASE_URI;

    @BeforeClass
    public static void init() {
        String env = System.getProperty("baseURI");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URI");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080/rest";
        }
        BASE_URI = env;
    }

    @Test(timeout = 60000)
    public void testSetDeViaAlpha_usTranslationsContainsDe() {
        given().baseUri(BASE_URI).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE_URI).when().get("/v1/alpha/US");
        act.then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testSetEsViaAlpha_usTranslationsContainsEs() {
        given().baseUri(BASE_URI).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE_URI).when().get("/v1/alpha/US");
        act.then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void testSetFrViaAlpha_usTranslationsContainsFr() {
        given().baseUri(BASE_URI).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE_URI).when().get("/v1/alpha/US");
        act.then().body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void testSetJaViaAlpha_usTranslationsContainsJa() {
        given().baseUri(BASE_URI).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE_URI).when().get("/v1/alpha/US");
        act.then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void testSetItViaAlpha_usTranslationsContainsIt() {
        given().baseUri(BASE_URI).when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().baseUri(BASE_URI).when().get("/v1/alpha/US");
        act.then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}