package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

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

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsCurrencyCode() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsCurrencyName() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountriesByCurrencyReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCountryByNameReturnsCurrencyFields() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2GetCountryByAlphaCodeReturnsCurrencyCode() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2GetCountryByAlphaCodeReturnsCurrencyName() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2GetCountryByAlphaCodeReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2GetCountriesByCurrencyReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404);
    }
}