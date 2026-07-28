package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    private static String base;

    @BeforeClass
    public static void init() {
        base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void test_alphaUS_containsDeTranslation() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/US");
        resp.then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void test_alphaUS_containsEsTranslation() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/US");
        resp.then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void test_alphaUS_containsFrTranslation() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/US");
        resp.then().body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void test_alphaUS_containsJaTranslation() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/US");
        resp.then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void test_alphaUS_containsItTranslation() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/v1/alpha/US");
        resp.then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}