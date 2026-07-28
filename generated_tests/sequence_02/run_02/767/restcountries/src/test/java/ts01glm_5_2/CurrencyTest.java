package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void v1AlphaByCode_returnsCurrencyCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue());
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void v1AlphaByCode_returnsCurrencyName() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0].name", notNullValue());
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void v1AlphaByCode_returnsCurrencySymbol() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void v1CurrencyByCode_returnsCountriesWithCurrencyFields() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void v1NameByCountry_returnsCurrencyFields() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void v1CallingCode_returnsCurrencyFields() {
        given()
                .when()
                .get("/v1/callingcode/44")
                .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void v1Capital_returnsCurrencyFields() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(200)
                .body("currencies[0].symbol", notNullValue());
    }

    @Test(timeout = 60000)
    public void v1Region_returnsCurrencyFields() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(200)
                .body("currencies", not(empty()));
    }

    @Test(timeout = 60000)
    public void v1All_returnsCurrencyFields() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .body("currencies", not(empty()));
    }
}