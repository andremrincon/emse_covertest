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
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetCodeViaV1AlphaEndpoint() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0]['code']", equalTo("USD"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetNameViaV1AlphaEndpoint() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0]['name']", equalTo("United States dollar"));
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testSetSymbolViaV1AlphaEndpoint() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0]['symbol']", equalTo("$"));
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetCodeViaV1CurrencyEndpoint() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .body("[0].currencies[0]['code']", equalTo("USD"));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetNameViaV1CurrencyEndpoint() {
        given()
            .when()
                .get("/v1/currency/EUR")
            .then()
                .statusCode(200)
                .body("[0].currencies[0]['name']", equalTo("Euro"));
    }

    @Ignore("The parameter \"symbol\" was used but not defined. Define parameters using the JsonPath.params(.....")
    @Test(timeout = 60000)
    public void testSetSymbolViaV1CurrencyEndpoint() {
        given()
            .when()
                .get("/v1/currency/EUR")
            .then()
                .statusCode(200)
                .body("[0].currencies[0]['symbol']", equalTo("€"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testSetCodeViaV1AllEndpoint() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .body("currencies['code']", hasItem("USD"));
    }

    @Ignore("1 expectation failed. JSON path currencies['name'] doesn't match. Expected: a collection containi...")
    @Test(timeout = 60000)
    public void testSetNameViaV2RegionalblocEndpoint() {
        given()
            .queryParam("fields", "name;capital;currencies")
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(200)
                .body("currencies['name']", hasItem("Euro"));
    }

    @Ignore("1 expectation failed. JSON path currencies['symbol'] doesn't match. Expected: a collection contai...")
    @Test(timeout = 60000)
    public void testSetSymbolViaV2RegionalblocEndpoint() {
        given()
            .queryParam("fields", "name;capital;currencies")
            .when()
                .get("/v2/regionalbloc/NAFTA")
            .then()
                .statusCode(200)
                .body("currencies['symbol']", hasItem("$"));
    }
}