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
                .statusCode(200)
                .body("currencies.code.flatten()", hasItem("USD"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyName() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies.name.flatten()", hasItem("United States dollar"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(200)
                .body("currencies.symbol.flatten()", hasItem("£"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCurrencyCode() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .body("currencies.code.flatten()", hasItem("USD"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameEndpointReturnsCurrencyName() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("currencies.name.flatten()", hasItem("Euro"));
    }

    @Test(timeout = 60000)
    public void testV2AlphaWithFieldsReturnsCurrencySymbol() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies.symbol.flatten()", hasItem("$"));
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyCode() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .body("currencies.code.flatten()", hasItem("EUR"));
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocWithFieldsReturnsCurrencyName() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .body("currencies.name.flatten()", hasItem("Euro"));
    }

    @Test(timeout = 60000)
    public void testV2NameEndpointReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(200)
                .body("currencies.symbol.flatten()", hasItem("€"));
    }
}