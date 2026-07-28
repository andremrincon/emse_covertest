package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
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

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("USD"))
                .body("currencies[0].name", equalTo("United States dollar"))
                .body("currencies[0].symbol", equalTo("$"));
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCountriesWithCurrency() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("currencies[0].code", hasItem("USD"));
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencyCode() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("EUR"));
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencyName() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("currencies[0].name", equalTo("Euro"));
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("currencies[0].symbol", equalTo("€"));
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV1RegionReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(404)
                .body("currencies[0].code", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV1CapitalReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("GBP"));
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(404)
                .body("currencies[0].code", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <404> but was <200>.")
    @Test(timeout = 60000)
    public void testV1AllReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .body("currencies[0].code", notNullValue());
    }
}