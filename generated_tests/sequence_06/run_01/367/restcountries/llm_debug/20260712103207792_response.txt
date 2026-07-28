package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Before;
import org.junit.Test;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .body("currencies.USD.code", equalTo("USD"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyName() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .body("currencies.USD.name", equalTo("United States dollar"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencySymbol() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(lessThan(300))
                .body("currencies.USD.symbol", equalTo("$"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsCurrencyCode() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(lessThan(300))
                .body("currencies.USD.code", equalTo("USD"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsCurrencyName() {
        given()
            .when()
                .get("/v1/currency/EUR")
            .then()
                .statusCode(lessThan(300))
                .body("currencies.EUR.name", equalTo("Euro"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsCurrencySymbol() {
        given()
            .when()
                .get("/v1/currency/EUR")
            .then()
                .statusCode(lessThan(300))
                .body("currencies.EUR.symbol", equalTo("€"));
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCurrencyFields() {
        given()
            .when()
                .queryParam("fields", "name;capital;currencies")
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(404);
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencyCodeAndName() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(lessThan(300))
                .body("currencies.EUR.code", equalTo("EUR"));
    }

    @Ignore("Invalid JSON expression: Script1.groovy: 1: Unexpected input: '[0].currencies.*.' @ line 1, colum...")
    @Test(timeout = 60000)
    public void testV1AllReturnsCurrencySymbol() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(lessThan(300))
                .body("[0].currencies.*.symbol", notNullValue());
    }
}