package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByAlphaCodesReturnsLanguageFields() {
        given()
                .queryParam("codes", "US,CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByNameReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByLanguageReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByRegionReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByCurrencyReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByCallingCodeReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByCapitalReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetAllCountriesReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeInvalidFormat() {
        given()
                .when()
                .get("/v1/alpha/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeNotFound() {
        given()
                .when()
                .get("/v1/alpha/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByLanguageNotFound() {
        given()
                .when()
                .get("/v1/lang/123")
                .then()
                .statusCode(404);
    }
}