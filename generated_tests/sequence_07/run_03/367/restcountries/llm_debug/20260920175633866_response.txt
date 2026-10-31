package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetCountriesByCurrencyEur() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByCurrencyXof() {
        given()
            .when()
                .get("/v2/currency/XOF")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeUs() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByNameGermany() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByRegionalBlocEuWithCurrenciesField() {
        given()
            .queryParam("fields", "name;capital;currencies")
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByCallingCode1WithCurrenciesField() {
        given()
            .queryParam("fields", "name;capital;currencies")
            .when()
                .get("/v2/callingcode/1")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByCapitalParis() {
        given()
            .when()
                .get("/v2/capital/Paris")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByRegionEuropeWithCurrenciesField() {
        given()
            .queryParam("fields", "name;capital;currencies")
            .when()
                .get("/v2/region/Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetAllCountries() {
        given()
            .when()
                .get("/v2/all")
            .then()
                .statusCode(404);
    }
}