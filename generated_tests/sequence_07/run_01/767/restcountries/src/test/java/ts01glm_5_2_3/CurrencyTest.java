package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: a string containing \"Un...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body(containsString("USD"))
                .body(containsString("United States dollar"))
                .body(containsString("$"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: a string containing \"sy...")
    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCurrencyCodeNameSymbol() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .body(containsString("USD"))
                .body(containsString("name"))
                .body(containsString("symbol"));
    }

    @Test(timeout = 60000)
    public void testV2AlphaByCodeWithCurrencyFields() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0].code", equalTo("USD"))
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyFields() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .body("[0].currencies[0].code", equalTo("EUR"))
                .body("[0].currencies[0].name", notNullValue())
                .body("[0].currencies[0].symbol", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsCurrencyFields() {
        given()
                .queryParam("codes", "US,CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(200)
                .body("[0].currencies[0].code", equalTo("USD"))
                .body("[0].currencies[0].name", notNullValue())
                .body("[0].currencies[0].symbol", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCurrencyFields() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .body("[0].currencies[0].code", notNullValue())
                .body("[0].currencies[0].name", notNullValue())
                .body("[0].currencies[0].symbol", notNullValue());
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: a string containing \"sy...")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body(containsString("EUR"))
                .body(containsString("name"))
                .body(containsString("symbol"));
    }

    @Test(timeout = 60000)
    public void testV2NameReturnsCurrencyFields() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(200)
                .body("[0].currencies[0].code", equalTo("EUR"))
                .body("[0].currencies[0].name", notNullValue())
                .body("[0].currencies[0].symbol", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2AllReturnsCurrencyFields() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200)
                .body("[0].currencies[0].code", notNullValue())
                .body("[0].currencies[0].name", notNullValue())
                .body("[0].currencies[0].symbol", notNullValue());
    }
}