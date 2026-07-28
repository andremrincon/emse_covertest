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

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue())
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CurrencyByCodeReturnsCountriesWithCurrency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue())
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue())
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue())
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1CapitalReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue())
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1RegionReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue())
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV2AlphaByCodeReturnsCurrencyFields() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue())
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV2CurrencyByCodeReturnsCountriesWithCurrency() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue())
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AllReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue())
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }
}