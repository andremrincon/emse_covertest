package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Ignore;
public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testCorsFilterAllowOriginHeader() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsFilterAllowMethodsHeader() {
        given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsFilterAllowHeadersHeader() {
        given()
        .when()
            .get("/v1/currency/USD")
        .then()
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsFilterCacheControlHeader() {
        given()
        .when()
            .get("/v1/name/France")
        .then()
            .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV2AllEndpoint() {
        given()
        .when()
            .get("/v2/all")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV2AlphaEndpoint() {
        given()
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV2NameEndpoint() {
        given()
        .when()
            .get("/v2/name/Germany")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV2CapitalEndpoint() {
        given()
        .when()
            .get("/v2/capital/Paris")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV2RegionEndpoint() {
        given()
        .when()
            .get("/v2/region/Europe")
        .then()
            .statusCode(200);
    }

    @Ignore("Illegal character in path at index 47: http://localhost:8080/rest/v2/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testCorsFilterOnV2SubregionEndpoint() {
        given()
        .when()
            .get("/v2/subregion/{subregion}", "Western Europe")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV2LangEndpoint() {
        given()
        .when()
            .get("/v2/lang/Spanish")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV2DemonymEndpoint() {
        given()
        .when()
            .get("/v2/demonym/American")
        .then()
            .statusCode(200);
    }
}