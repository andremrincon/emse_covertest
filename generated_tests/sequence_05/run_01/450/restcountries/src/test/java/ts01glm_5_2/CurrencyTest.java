package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("baseUrl"));
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencyCode() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0].code", equalTo("USD"));
    }

    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencyName() {
        given()
                .when()
                .get("/v2/alpha/DE")
                .then()
                .statusCode(200)
                .body("currencies[0].name", equalTo("Euro"));
    }

    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v2/alpha/GB")
                .then()
                .statusCode(200)
                .body("currencies[0].symbol", equalTo("£"));
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyCode() {
        given()
                .when()
                .get("/v2/currency/USD")
                .then()
                .statusCode(200)
                .body("currencies[0].code", hasItem("USD"));
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
    public void testV2CurrencyEndpointReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v2/currency/GBP")
                .then()
                .statusCode(200)
                .body("currencies[0].symbol", hasItem("£"));
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .body("currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2NameReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/name/France")
                .then()
                .statusCode(200)
                .body("currencies[0].code", hasItem("EUR"));
    }

    @Test(timeout = 60000)
    public void testV2AllReturnsCurrencyFields() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/all")
                .then()
                .statusCode(200)
                .body("currencies", notNullValue());
    }
}