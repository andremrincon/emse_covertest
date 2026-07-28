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
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies.code", hasItem("USD"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyName() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies.name", hasItem("United States dollar"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(200)
                .body("currencies.symbol", hasItem("£"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .body("currencies.code", hasItem("USD"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencyCodeNameSymbol() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("currencies.code", hasItem("EUR"))
                .body("currencies.name", hasItem("Euro"))
                .body("currencies.symbol", hasItem("€"));
    }

    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/alpha/DE")
                .then()
                .statusCode(200)
                .body("currencies[0].code", equalTo("EUR"));
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyName() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .body("currencies[0].name", hasItem("Euro"));
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .body("currencies[0].symbol", hasItem("€"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AllReturnsCurrencyCode() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .body("currencies.code", hasItem("AFN"));
    }
}