package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testDeTranslationViaAlpha() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testEsTranslationViaName() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .body("[0].translations.es", equalTo("Francia"));
    }

    @Test(timeout = 60000)
    public void testFrTranslationViaCurrency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .body("[0].translations.fr", equalTo("Samoa américaines"));
    }

    @Test(timeout = 60000)
    public void testJaTranslationViaCallingcode() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(200)
                .body("[0].translations.ja", equalTo("アメリカ領サモア"));
    }

    @Test(timeout = 60000)
    public void testItTranslationViaRegion() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(200)
                .body("[0].translations.it", equalTo("Isole Aland"));
    }
}