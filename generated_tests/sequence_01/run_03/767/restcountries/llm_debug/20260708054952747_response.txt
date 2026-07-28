package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.Optional;

public class CountryTranslationsTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("baseUrl");
        String env = System.getenv("BASE_URL");
        BASE = Optional.ofNullable(prop).orElse(Optional.ofNullable(env).orElse("http://localhost:8080/rest"));
    }

    @Test(timeout = 60000)
    public void testSetDe_v1Alpha_US_translations_de() {
        given().baseUri(BASE).when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/v1/alpha/US");
        resp.then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testSetEs_v1Alpha_US_translations_es() {
        given().baseUri(BASE).when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/v1/alpha/US");
        resp.then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void testSetFr_v1Alpha_US_translations_fr() {
        given().baseUri(BASE).when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/v1/alpha/US");
        resp.then().body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void testSetJa_v1Alpha_US_translations_ja() {
        given().baseUri(BASE).when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/v1/alpha/US");
        resp.then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void testSetIt_v1Alpha_US_translations_it() {
        given().baseUri(BASE).when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().baseUri(BASE).when().get("/v1/alpha/US");
        resp.then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}