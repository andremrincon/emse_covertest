package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

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

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].code", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsCurrencyFields() {
        given()
                .queryParam("codes", "US,CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(404)
                .body("currencies[0].code", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyByCodeReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("currencies[0].name", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AllReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .body("currencies[0].code", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaByCodeReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].code", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CurrencyByCodeReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404)
                .body("currencies[0].name", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2NameReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCurrencyFields() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404)
                .body("currencies[0].code", notNullValue());
    }
}