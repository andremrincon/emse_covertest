package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv("baseUrl"));
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsAccessControlAllowOriginHeader() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsAccessControlAllowMethodsHeader() {
        given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsAccessControlAllowHeadersHeader() {
        given()
        .when()
            .get("/v1/name/France")
        .then()
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsCacheControlHeader() {
        given()
        .when()
            .get("/v1/callingcode/1")
        .then()
            .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsAllCORSHeadersOnRegionEndpoint() {
        given()
        .when()
            .get("/v1/region/Europe")
        .then()
            .header("Access-Control-Allow-Origin", nullValue())
            .header("Access-Control-Allow-Methods", nullValue())
            .header("Access-Control-Allow-Headers", nullValue())
            .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsCORSHeaderOnCapitalEndpoint() {
        given()
        .when()
            .get("/v1/capital/London")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsCORSHeaderOnCurrencyEndpoint() {
        given()
        .when()
            .get("/v1/currency/USD")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsCORSHeaderOnAlphaCodesEndpoint() {
        given()
        .when()
            .get("/v1/alpha?codes=US,CA,MX")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsCORSHeaderOnSubregionEndpoint() {
        given()
        .when()
            .get("/v1/subregion/Western%20Europe")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsCORSHeaderOnLanguageEndpoint() {
        given()
        .when()
            .get("/v1/lang/es")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsCORSHeaderOnBadRequestResponse() {
        given()
        .when()
            .get("/v1/alpha/123")
        .then()
            .statusCode(404)
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testDoFilterAddsCORSHeaderOnNotFoundResponse() {
        given()
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .statusCode(404)
            .header("Access-Control-Allow-Origin", nullValue());
    }
}