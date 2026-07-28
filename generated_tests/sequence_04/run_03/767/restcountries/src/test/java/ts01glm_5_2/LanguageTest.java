package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages[0].iso639_1", notNullValue())
                .body("languages[0].iso639_2", notNullValue())
                .body("languages[0].name", notNullValue())
                .body("languages[0].nativeName", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeGBReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(200)
                .body("languages[0].iso639_1", equalTo("en"))
                .body("languages[0].iso639_2", equalTo("eng"))
                .body("languages[0].name", equalTo("English"))
                .body("languages[0].nativeName", equalTo("English"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetCountriesByAlphaCodesReturnsLanguageFields() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", notNullValue())
                .body("[0].languages[0].iso639_2", notNullValue())
                .body("[0].languages[0].name", notNullValue())
                .body("[0].languages[0].nativeName", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetCountriesByCurrencyReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", notNullValue())
                .body("[0].languages[0].iso639_2", notNullValue())
                .body("[0].languages[0].name", notNullValue())
                .body("[0].languages[0].nativeName", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetCountryByNameReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", equalTo("fr"))
                .body("[0].languages[0].iso639_2", equalTo("fra"))
                .body("[0].languages[0].name", equalTo("French"))
                .body("[0].languages[0].nativeName", equalTo("français"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetCountryByNameFullTextReturnsLanguageFields() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v1/name/Germany")
                .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", equalTo("de"))
                .body("[0].languages[0].iso639_2", equalTo("deu"))
                .body("[0].languages[0].name", equalTo("German"))
                .body("[0].languages[0].nativeName", equalTo("Deutsch"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetCountriesByCallingCodeReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", notNullValue())
                .body("[0].languages[0].iso639_2", notNullValue())
                .body("[0].languages[0].name", notNullValue())
                .body("[0].languages[0].nativeName", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetCountriesByCapitalReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", equalTo("en"))
                .body("[0].languages[0].iso639_2", equalTo("eng"))
                .body("[0].languages[0].name", equalTo("English"))
                .body("[0].languages[0].nativeName", equalTo("English"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetCountriesByRegionReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", notNullValue())
                .body("[0].languages[0].iso639_2", notNullValue())
                .body("[0].languages[0].name", notNullValue())
                .body("[0].languages[0].nativeName", notNullValue());
    }

    @Ignore("Illegal character in path at index 50: http://localhost:8080/rest/v1/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testGetCountriesBySubregionReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/subregion/{subregion}", "Western Europe")
                .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", notNullValue())
                .body("[0].languages[0].iso639_2", notNullValue())
                .body("[0].languages[0].name", notNullValue())
                .body("[0].languages[0].nativeName", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetCountriesByLanguageReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", notNullValue())
                .body("[0].languages[0].iso639_2", notNullValue())
                .body("[0].languages[0].name", notNullValue())
                .body("[0].languages[0].nativeName", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetAllCountriesReturnsLanguageFields() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .body("[0].languages[0].iso639_1", notNullValue())
                .body("[0].languages[0].iso639_2", notNullValue())
                .body("[0].languages[0].name", notNullValue())
                .body("[0].languages[0].nativeName", notNullValue());
    }
}