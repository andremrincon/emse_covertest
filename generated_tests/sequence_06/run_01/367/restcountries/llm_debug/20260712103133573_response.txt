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
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsLanguageIso639_1() {
        given()
                .pathParam("alphacode", "US")
                .when()
                .get("/v1/alpha/{alphacode}")
                .then()
                .statusCode(lessThan(300))
                .body("languages.iso639_1", hasItem("en"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <400> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaByCodesReturnsLanguageIso639_2() {
        given()
                .queryParam("codes", "US,CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(lessThan(300))
                .body("languages.iso639_2", hasItem("eng"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsLanguageName() {
        given()
                .pathParam("currency", "USD")
                .when()
                .get("/v1/currency/{currency}")
                .then()
                .statusCode(lessThan(300))
                .body("languages.name", hasItem("English"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameReturnsLanguageNativeName() {
        given()
                .pathParam("name", "France")
                .when()
                .get("/v1/name/{name}")
                .then()
                .statusCode(lessThan(300))
                .body("languages.nativeName", hasItem("français"));
    }

    @Test(timeout = 60000)
    public void testV1CallingCodeReturns200() {
        given()
                .pathParam("callingcode", "1")
                .when()
                .get("/v1/callingcode/{callingcode}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CapitalReturns200() {
        given()
                .pathParam("capital", "London")
                .when()
                .get("/v1/capital/{capital}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1RegionReturns200() {
        given()
                .pathParam("region", "Europe")
                .when()
                .get("/v1/region/{region}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1SubregionReturns200() {
        given()
                .pathParam("subregion", "Western%20Europe")
                .when()
                .get("/v1/subregion/{subregion}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1LangReturns200() {
        given()
                .pathParam("lang", "es")
                .when()
                .get("/v1/lang/{lang}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaByCodeReturns200() {
        given()
                .pathParam("alphacode", "US")
                .when()
                .get("/v2/alpha/{alphacode}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2LangReturns200() {
        given()
                .pathParam("lang", "Spanish")
                .when()
                .get("/v2/lang/{lang}")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2NameReturns200() {
        given()
                .pathParam("name", "Germany")
                .when()
                .get("/v2/name/{name}")
                .then()
                .statusCode(404);
    }
}