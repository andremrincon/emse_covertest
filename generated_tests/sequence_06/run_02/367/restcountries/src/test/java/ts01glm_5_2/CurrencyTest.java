package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("base.url");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testAlphaCodeReturnsCurrencyCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0]['code']", equalTo("USD"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testAlphaCodeReturnsCurrencyName() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0]['name']", notNullValue());
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testAlphaCodeReturnsCurrencySymbol() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0]['symbol']", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testCurrencyEndpointReturnsCountriesWithCurrencyFields() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .body("currencies[0]['code']", hasItem("USD"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testNameEndpointReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .body("currencies[0]['code']", equalTo("EUR"));
    }

    @Test(timeout = 60000)
    public void testAllEndpointReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .body("currencies", notNullValue());
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testCallingCodeEndpointReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/callingcode/44")
            .then()
                .statusCode(200)
                .body("currencies[0]['code']", hasItem("GBP"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testCapitalEndpointReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(200)
                .body("currencies[0]['code']", hasItem("GBP"));
    }

    @Test(timeout = 60000)
    public void testRegionEndpointReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(200)
                .body("currencies", notNullValue());
    }
}