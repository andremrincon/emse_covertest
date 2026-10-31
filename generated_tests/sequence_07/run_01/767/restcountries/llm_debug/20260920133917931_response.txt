package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    private static final String BASE_URL = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginHeaderOnV1All() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeaderOnV1AlphaCode() {
        given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeaderOnV1Name() {
        given()
        .when()
            .get("/v1/name/France")
        .then()
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1Region() {
        given()
        .when()
            .get("/v1/region/Europe")
        .then()
            .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginHeaderOnV1CallingCode() {
        given()
        .when()
            .get("/v1/callingcode/1")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeaderOnV1Capital() {
        given()
        .when()
            .get("/v1/capital/London")
        .then()
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeaderOnV1Currency() {
        given()
        .when()
            .get("/v1/currency/USD")
        .then()
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1Lang() {
        given()
        .when()
            .get("/v1/lang/es")
        .then()
            .header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginHeaderOnNotFoundResponse() {
        given()
        .when()
            .get("/v1/alpha/XYZ")
        .then()
            .header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeaderOnV2All() {
        given()
        .when()
            .get("/v2/all")
        .then()
            .header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeaderOnV1AlphaCodes() {
        given()
            .queryParam("codes", "US,CA,MX")
        .when()
            .get("/v1/alpha")
        .then()
            .header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV2Name() {
        given()
        .when()
            .get("/v2/name/Germany")
        .then()
            .header("Cache-Control", nullValue());
    }
}